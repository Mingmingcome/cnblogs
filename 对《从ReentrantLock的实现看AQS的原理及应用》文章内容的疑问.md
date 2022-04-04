## 对《从ReentrantLock的实现看AQS的原理及应用》文章内容的疑问


文章链接附上：[从ReentrantLock的实现看AQS的原理及应用](https://tech.meituan.com/2019/12/05/aqs-theory-and-apply.html)

在<b>【2.2 AQS重要方法与ReentrantLock的关联】</b>中

根据jdk1.8.0_162的源码中已在图中标注本人的疑问点

```java
final boolean nonfairTryAcquire(int acquires) {
	final Thread current = Thread.currentThread();
	int c = getState();
	if (c == 0) {
	    if (compareAndSetState(0, acquires)) {
	        setExclusiveOwnerThread(current);
	        return true;
	    }
	}
	else if (current == getExclusiveOwnerThread()) {
	    int nextc = c + acquires;
	    if (nextc < 0) // overflow
	        throw new Error("Maximum lock count exceeded");
	    setState(nextc);
	    return true;
	}
	return false;
}
```

![已标注的图片](https://images.cnblogs.com/cnblogs_com/mingmingcome/2137704/o_220403050528_nofairLock&AQS.png)
