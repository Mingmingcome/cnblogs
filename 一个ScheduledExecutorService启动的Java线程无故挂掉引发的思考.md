`2018年12月12日18:44:53`

## 一个ScheduledExecutorService启动的Java线程无故挂掉引发的思考

####  案件现场

不久前，在开发改造公司一个端到端监控日志系统的时候，出现了一个bug：有个扫表写日志的线程无故挂掉。

#### 顺藤摸瓜

我看了很久的代码，都没有想出来有什么地方有逻辑问题。万金油的方法是，重启。当我满心欢喜地认为重启是个好方法的时候，问题又重现了。

我有点无奈地看着自己的代码

>本我：堪称完美的逻辑，还有什么地方是我没有注意到的吗？

>真我：当然有了，你这个菜鸟，你不知道的地方多着呢。

于是，去找老大问一下问题怎么解决，老大说去生产数据库上导十万数据到测试库，然后在本地debug一下。接着，我就从数据库里面导出一万数据开始测试，在eclipse启动进程，日志写在本地文件。很快，问题再一次出现。然后断点，然后找到出问题的地方。出问题的地方如下：

![问题地点](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_ScheduledExecutorService-throw-Exception.png)

代码就一行：

```java
String timeStamp = DateUtil.str2Date(receiveTime, DateUtil.YYYYMMDDHH24MISS).getTime() + "000";
```

这行代码的意思是，将字符串的接收时间receiveTime格式化，getTime()得到时间戳，因为格式是要微秒，加了三个零。

DateUtil.str2Date方法：（String时间转化为Date类型，关于时间转换可以看看本人的[String、Date和Timestamp的互转](https://www.cnblogs.com/mingmingcome/p/9514601.html)）

```java
public static Date str2Date(String dateStr, String dateFormat){
	if (StringHelper.isEmpty(dateStr)) {
		return null;
	}

	SimpleDateFormat df = new SimpleDateFormat(dateFormat);
	try {
		return df.parse(dateStr);
	} catch (Exception ex) {
		return null;
	}
}
```

这个工具类当parse方法抛出异常的时候返回null，看起来是没有问题的，但是我在转换之后没有判断是否为空即`null`，然后就变成了`null.getTime()`，接着就抛了一个很常见的`NullPointerException`异常。

到这里，看似问题已经解决了，但是问题并没有那么简单。

#### 寻根问底

上面说到的在线程中抛出了`NullPointerException`异常，解决方法是增加一个判断是否为空的条件就可以了。但是一般来说，有异常的时候，程序没有捕获异常，日志里或者debug时控制台会打印异常信息，类似这种：

```java
at com.netease.backend.rds.task.CleanHandleThread.run(CleanHandleThread.java:65)
at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:439)
at java.util.concurrent.FutureTask$Sync.innerRunAndReset(FutureTask.java:317)
at java.util.concurrent.FutureTask.runAndReset(FutureTask.java:150)
at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$101(ScheduledThreadPoolExecutor.java:98)
at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.runPeriodic(ScheduledThreadPoolExecutor.java:180)
at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:204)
at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:895)
at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:918)
at java.lang.Thread.run(Thread.java:662)
```

但实际上我debug的时候，并没有看到打印的异常信息。我是断点到这一步，发现下一行代码没有执行，我就断定问题是在这里，而且空指针异常一下子就能看出来了。问题来了，为什么没有打印异常信息呢？我想应该是线程的问题，代码里启动这个写日志的定时任务用的是ScheduledExecutorService：

![ScheduledExecutorService](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_ScheduledExecutorService-execute-thread.png)

我Google了一下，发现其实有很多前辈都曾遇到过这个问题。

![Google搜索](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_ScheduledExecutorService-Google-search.png)

在这些文章中，我找到了我要的答案。我引用其中的一篇文章[从一个java线程挂掉的例子讨论分析定位问题基本原则](http://mingxinglai.com/cn/2016/05/java-thread-crash/)文字作为答案吧。

>那么，Java线程挂掉的主要原因是：Any thrown exception or error reaching the executor causes the executor(ScheduledExecutorService) to halt. No more invocations on the Runnable, no more work done. This work stoppage happens silently, you’ll not be informed.

>也就是说，如果使用者抛出异常，ScheduledExecutorService 将会停止线程的运行，而且不会报错，没有任何提示信息。

这就是在日志中和控制台都没有看到打印异常信息的原因。

#### 解决方法

写了一个测试类，有兴趣可以研究一下这个bug。
```java
public class ScheduledExecutorServiceThrowExceptionTest {
	
	private static int i = 0;
	
	public static void main(String[] args) {
		ScheduledExecutorService exc = Executors.newSingleThreadScheduledExecutor();
		exc.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				i++;
				if (i==6) {
					throw new RuntimeException();
				} else {
					System.out.println(i);
				}
				
			}
			
		}, 0, 1, TimeUnit.SECONDS);
			
	}

}
```

测试结果是：

![测试结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_ScheduledExecutorService-test-result.png)

结果显示，当程序抛出异常的时候，线程就不再运行了，也就是挂了。

解决方法：

- 1、直接加一个`try-catch`进行异常捕获，然后你可以打印你需要的异常信息或者处理异常。
```java
public class ScheduledExecutorServiceThrowExceptionTest1 {
	
	private static int i = 0;
	
	public static void main(String[] args) {
		ScheduledExecutorService exc = Executors.newSingleThreadScheduledExecutor();
		exc.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				try {
					// doSomething();// TODO：具体业务逻辑
					i++;
					if (i==6) {
						throw new RuntimeException();
					} else {
						System.out.print(i + " ");
					}
				} catch (Exception ex) {
					System.out.println();
					System.out.println("在ScheduledExecutorService中有异常抛出，异常堆栈：" + ex.getStackTrace());
				}
			}
			
		}, 0, 1, TimeUnit.SECONDS);
			
	}

}
```
结果是打印了异常信息，且线程没有被中断。
```java
1 2 3 4 5 
在ScheduledExecutorService中有异常抛出，异常堆栈：[Ljava.lang.StackTraceElement;@1bb53ed8
7 8 9 10 11 12 13 
```

- 2、通过ScheduledFuture对象返回异常信息
```java
public class ScheduledExecutorServiceThrowExceptionTest2 {

	private static int i = 0;
	
	public static void main(String[] args) {
		ScheduledExecutorService exc = Executors.newSingleThreadScheduledExecutor();
		ScheduledFuture<?> handle = exc.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				i++;
				if (i==6) {
					throw new RuntimeException();
				} else {
					System.out.print(i + " ");
				}
				
			}
			
		}, 0, 1, TimeUnit.SECONDS);
		
		try {
			handle.get();
		} catch(Exception ex) {
			System.out.println();
			System.out.println("在ScheduledExecutorService中有异常抛出，异常堆栈：" + ex.getStackTrace());
		}
			
	}

}
```
这个解决方法打印了异常信息，但是并没有阻止线程挂掉。
```java
1 2 3 4 5 
在ScheduledExecutorService中有异常抛出，异常堆栈：[Ljava.lang.StackTraceElement;@33909752
```

#### 总结

一个ScheduledExecutorService启动的Java线程无故挂掉的原因是：如果使用者抛出异常，ScheduledExecutorService 将会停止线程的运行，而且不会报错，没有任何提示信息。解决方法是：`try-catch`将异常信息打印，或者用ScheduledFuture<?>获取线程运行结果。

写的bug多，自然经验就多了，但是要注意总结。

#### 完

`2018年12月13日09:08:19`
