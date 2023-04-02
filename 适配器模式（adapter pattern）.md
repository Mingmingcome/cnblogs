`2018年10月17日08:50:11`

## 适配器模式（adapter pattern）

#### 定义

>我喜欢的样子你都有

>你喜欢的样子我有没有

>没有的话，我送你个适配器，好吗

将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能在一起工作的那些类可以一起工作。——《设计模式：可复用面向对象软件的基础》

适配器模式是一种对象结构型模式。

这里的接口不仅仅是java语言中的interface，更多是指一个类型所具有的方法特征集合，是一种逻辑上的抽象。

#### 使用场景

客户端需要一个target（目标）接口，但是不能直接重用已经存在的adaptee（适配者）类，因为它的接口和target接口不一致，所以需要adapter（适配器）将adaptee转换为target接口。前提是target接口和已存在的适配者adaptee类所做的事情是相同或相似，只是接口不同且都不易修改。如果在设计之初，最好不要考虑这种设计模式。凡事都有例外，就是设计新系统的时候考虑使用第三方组件，因为我们就没必要为了迎合它修改自己的设计风格，可以尝试使用适配器模式。

这是一个适配器使用场景的例子：

Sun公司在1996年公开了Java语言的数据库连接工具JDBC，JDBC使得Java语言程序能够与数据库连接，并使用SQL语言来查询和操作数据。JDBC给出一个客户端通用的抽象接口，每一个具体数据库引擎（如SQL Server、Oracle、MySQL等）的JDBC驱动软件都是一个介于JDBC接口和数据库引擎接口之间的适配器软件。抽象的JDBC接口和各个数据库引擎API之间都需要相应的适配器软件，这就是为各个不同数据库引擎准备的驱动程序。

#### 角色

目标角色（target）：这是客户锁期待的接口。目标可以是具体的或抽象的类，也可以是接口

适配者角色（adaptee）：已有接口，但是和客户器期待的接口不兼容。

适配器角色（adapter）：将已有接口转换成目标接口。

#### 分类

适配器模式有分三类：

- 1、类适配器模式（class adapter pattern）

- 2、对象适配器模式（object adapter pattern）

- 3、缺省适配器模式（default adapter pattern），也叫默认适配器模式、接口适配器模式

### 类适配器模式（class  adapter pattern）

类适配器模式在编译时实现target（目标）接口。这种适配器模式使用了多个实现了期待的接口或者已经存在的接口的多态接口。比较典型的就是：target接口被创建为一个纯粹的接口，如Java不支持多继承的语言。

#### 图示

类适配器模式（adapter pattern）结构图：
![类适配器模式](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_adapter-class-adapter.jpg)

如上图，因为java没有类多继承，所以只能实现Target接口，而且Target只能是接口。Adapter实现了Target接口，继承了Adaptee类，Target.operation()实现为Adaptee.specificOperation()。

客户端调用类适配器：
![客户端调用类适配器](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_adapter-class-adapter.png)

这个图是Adapter适配器多继承的情况，引用维基百科，可以看到客户端调用适配器Adapter的methodA时候，实际上调用的是Adapter继承过来的method1到methodN。

#### 代码示例

一张图说明需求：

![电源适配器](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_adapter-power-adapter.jpg)

嗯，就是电源适配器了。上面有这样两行：
> 输入：100-240V ~ 0.5A 50-60HZ
 
> 输出：5.2V ==== 2.4A

我们的需求就是将电源输入220V（适配者）转换为5V输出（目标）。

目标角色（PowerTarget.java）：
```java
public interface PowerTarget {
	public int output5V();
}
```
电源目标。

适配者角色（PowerAdaptee.java）：
```java
public class PowerAdaptee {
	private int output =  220;
	public int output220V() {
		System.out.println("电源输出电压：" + output);
		return output;
	}
}
```
电源适配者。

适配器角色（PowerAdapter.java）：
```java
public class PowerAdapter extends PowerAdaptee implements PowerTarget{
	
	@Override
	public int output5V() {
		int output = output220V();
		System.out.println("电源适配器开始工作，此时输出电压是：" + output);
		output = output/44;
		System.out.println("电源适配器工作完成，此时输出电压是：" + output);
		return output;
	}
	
}
```
电源适配器类实现了电源目标，继承了适配者。其实如果没有我打印的那些提示或者说日志，output5V方法可以直接写成：
```java
public int output5V() {
		return output220V()/44;
	}
```
这样就适配了。

类适配器模式测试类（ClassAdapterPatternTest.java）：
```java
public class ClassAdapterPatternTest {
	
	public static void main(String[] args) {
		PowerTarget target = new PowerAdapter();
		target.output5V();
	}
}
```

测试结果：
![适配器模式示例测试结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_adapter-instance-result.jpg)

### 对象适配器模式（object  adapter pattern）

对象适配器模式在运行时实现target（目标）接口。在这种适配器模式中，适配器包装了一个类实例。在这种情况下，适配器调用包装对象实例的方法。

#### 图示

对象适配器模式（object adapter pattern）结构图：
![对象适配器模式结构图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_adapter-object-adapter.jpg)

如上图，与类适配器模式不同的是，Adapter只实现了Target的接口，没有继承Adaptee，而是使用聚合的方式引用adaptee。

客户端调用对象适配器：
![客户端调用对象适配器](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_adapter-object-adapter.png)

客户端调用对象适配器方法methodA的时候，实际上调用的是创建对象传进来的适配者实例的方法methodB。

#### 代码示例

代码示例和类适配器模式只有Adapter类有不同，其他完成一样，连测试结果都是一样。下面只贴上Adapter类。

适配器角色（Adapter.java）：
```java
public class PowerAdapter implements PowerTarget{
	private PowerAdaptee powerAdaptee;

	public PowerAdapter(PowerAdaptee powerAdaptee) {
		super();
		this.powerAdaptee = powerAdaptee;
	}

	@Override
	public int output5V() {
		int output = powerAdaptee.output220V();
		System.out.println("电源适配器开始工作，此时输出电压是：" + output);
		output = output/44;
		System.out.println("电源适配器工作完成，此时输出电压是：" + output);
		return output;
	}
	
}
```
实现了PowerTarget（目标角色），在创建对象时引入PowerAdaptee（适配者角色）。

#### 类适配器模式和对象适配器模式的对比

##### 优点

类适配器模式（class adapter pattern）：

由于适配器adapter类是适配者adaptee类的子类，因此可以在适配器类中置换一些适配者的方法，即Override（重写），使得适配器的灵活性更强。

对象适配器模式（object adapter pattern）：

一个对象适配器可以把多个不同的适配者adaptee适配到一个目标，也就是说，同一个适配器可以将适配者类和它的子类都适配到目标接口。

##### 缺点

类适配器模式：

java8之前：接口没有default方法，就是没有实现了具体逻辑的方法，而且不支持类多继承，所以<b>适配者类只能有一个</b>。

java8之后：接口有了default方法，接口中的方法有了实现，因为接口是多继承的，所以适配者可以是多个带有default方法的接口，但是接口是不可以实例化的，实际上没有什么意义。有个解决方法就是，接口里都是default方法，实现接口的类什么也没做，就是提供一个可以实例化的类。这样的化，类适配器模式中适配者adapter类就可以适配多个适配者adaptee类了。这个解决方法只是我理论上论证一下，实际上可行与否，请自行判断验证。

对象适配器模式：

类适配器模式的优点就是对象适配器模式的缺点，不能置换适配者类的方法。如果想修改适配者类的一个或多个方法，就只好先创建一个继承与适配者类的子类，把适配者类的方法置换掉，然后把适配者的子类当做真正的适配者进行适配，实现过程较为复杂。

### 缺省适配器模式（default  adapter pattern）

当不需要全部实现接口提供的方法时，可以设计一个适配器抽象类实现接口，并为接口中的每个方法提供默认方法，抽象类的子类就可以有选择的覆盖父类的某些方法实现需求，它适用于一个接口不想使用所有的方法的情况。在java8后，接口中可以有default方法，就不需要这种缺省适配器模式了。接口中方法都设置为default，实现为空，这样同样同样可以达到缺省适配器模式同样的效果。

#### 图示

缺省适配器模式结构图：
![缺省适配器模式结构图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_adapter-default-adapter.jpg)

适配器Adapter类实现Target接口，方法默认为空。

#### 代码示例

目标角色（SampleOperation.java）：
```java
public interface SampleOperation {
	public abstract void operation1();
	public abstract void operation2();
	public abstract void operation3();
	public abstract void operation4();
	public abstract void operation5();
}

```
包含了很多操作。

适配器角色（DefaultAdapter.java）：
```java
public abstract class DefaultAdapter implements SampleOperation{

	@Override
	public void operation1() {
	}

	@Override
	public void operation2() {
	}

	@Override
	public void operation3() {
	}

	@Override
	public void operation4() {
	}

	@Override
	public void operation5() {
	}
}
```
默认实现了所有操作

这个是测试缺省适配器模式需要用到的类（Operator.java）：
```java
public class Operator {
	private SampleOperation sampleOperation;
	
	public void addOperation(SampleOperation sampleOperation) {
		this.sampleOperation = sampleOperation;
	}

	public void operation1() {
		sampleOperation.operation1();
	}

	public void operation2() {
		sampleOperation.operation2();
	}

	public void operation3() {
		sampleOperation.operation3();
	}

	public void operation4() {
		sampleOperation.operation4();
	}

	public void operation5() {
		sampleOperation.operation5();
	}
}
```

缺省适配器模式测试类（DefaultAdapterTest.java）：
```java
public class DefaultAdapterTest {

	public static void main(String[] args) {
		
		// 1、原来要实现所有操作类的操作
		Operator operator1 = new Operator();
		operator1.addOperation(new SampleOperation() {

			@Override
			public void operation1() {}

			@Override
			public void operation2() {
				System.out.println("操作2");
			}

			@Override
			public void operation3() {}

			@Override
			public void operation4() {}

			@Override
			public void operation5() {}
			
		});
		operator1.operation2();
		
		// 2、使用缺省适配器只需要实现需要用到的接口方法
		Operator operator2 = new Operator();
		operator2.addOperation(new DefaultAdapter() {
			
			@Override
			public void operation2() {
				System.out.println("操作2");
			}
		});
		operator2.operation2();
	}
}
```
测试类需要执行操作2，operator1添加SampleOperation时要实现接口里所有方法，operator2添加SampleOperation时只需要通过DefaultAdapter适配器添加自己需要的操作即可。毫无疑问，测试结果是一样的。
![缺省适配器模式测试结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_adapter-default-adapter-result.jpg)

#### 优点

1、复用性：系统需要使用已经存在的类，功能符合系统要求，但这个类的接口不符合系统的需求，通过适配器模式解决不兼容的问题，使这些功能类得到复用。

2、扩展性：适配器使得系统多了一个方式扩展系统的功能

3、耦合性：一定程度上的解耦

#### 缺点

过多地使用适配器，增加系统理解难度。

#### 总结

本文主要介绍了三种适配器模式，本质上是现有的不兼容的接口转换为需要的接口。

类适配器模式，以继承现有类的方式转换。

对象适配器模式，以聚合对象实例的方式转换。

接口适配器模式，以实现接口的方式转换。

适配器模式是在现有的类和系统都不易修改的情况下使用，在系统设计之初慎用适配器模式。

#### 完

`2018年10月18日14:53:33`