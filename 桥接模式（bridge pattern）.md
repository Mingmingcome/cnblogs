`2018年11月2日09:26:00`

## 桥接模式（bridge pattern）

#### 定义

>记忆中最深的就是那个外婆桥

>家和外婆在两边

>在家里想吃和想玩的

>在外婆家都有

桥接模式（bridge pattern），将抽象部分与它的实现部分分离，是它们都可以独立的变化。————《设计模式：可复用面向对象软件的基础》

桥接模式是一种对象结构型模式。

#### 使用场景

1、如果一个系统需要在构件的抽象化角色和实现化角色之间增加更多的灵活性，避免在两个层次之间建立静态的继承联系，通过桥接模式可以使它们在抽象层建立一个关联关系。

2、抽象化角色和实现化角色可以以继承的方式独立扩展而互不影响，在程序运行时可以动态将一个抽象化子类的对象和一个实现化子类的对象进行组合，即系统需要对抽象化角色和实现化角色进行动态耦合。

3、一个类存在两个独立变化的维度，且这两个维度都需要进行扩展。

4、虽然在系统中使用继承是没有问题的，但是由于抽象化角色和具体化角色需要独立变化，设计要求需要独立管理这两者。对于那些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统，桥接模式尤为适用。

<b>抽象和实现分离，各自变化。</b>

<b>类存在两个独立变化的维度，抽象代表一个，实现代表一个。</b>

#### 组合/聚合复用原则

组合/聚合复用原则（CARP：Composite/Aggregate Reuse Priciple），尽量使用组合/聚合复用原则，尽量不要使用类继承。————《Java与设计模式》

聚合表示一种弱的‘拥有’关系，体现的是A对象可以包含B对象，但B对象不是A对象的一部分；组合则是一种强的‘拥有’关系，体现了严格的部分和整体的关系，部分和整体的生命周期一样。————《设计模式解释》

组合/聚合复用原则的好处是，优先使用对象的组合/聚合复用原则将有助于你保持每个类被封装，并被集中在单个任务上，这样类和类继承层次会保持较小规模，并且不太可能增长为不可控制的庞然大物。————《设计模式：可复用面向对象软件的基础》

组合关系就是一个人有手脚，聚合是一个人有钱。

示例代码：
``` java
public class Person {
	private Hand hand = new Hand();
	private Foot foot = new Foot();
	private Money money;

	public Person(){}
	public Person(Money money){
		this.money = money;
	}
}
public class Hand{}
public class Foot{}
public class Money{}
```
手脚是一个正常人生来就有的，但是钱这个东西，身外之物。聚合是松耦合的，组合相对来说就是耦合性比较强。

桥接模式是应用组合/聚合复用原则（CARP）。其实还有很多其他对象结构型模式都是应用了这个原则的，如代理模式、装饰者模式、对象适配者模式等，以此来达到松耦合的目的。

#### 角色

抽象化角色（Abstraction）：抽象化给出的定义，并保存一个对实现化对象的引用

扩充抽象化角色（RefinedAbstraction）【修正抽象化(Refined Abstraction)角色】：扩展抽象化角色，改变和修正父类对抽象化的定义

实现化角色（Implementor）：实现化角色的接口，但不给出具体的实现

具体实现化角色（ConcreteImplementor）：具体实现

#### 图示

![桥接模式结构图](https://raw.githubusercontent.com/Mingmingcome/cnblogs/master/images/bridge-pattern-structure.jpg)

抽象（Abstraction）和实现（Implementor）以组合的方式关联起来，抽象的动作行为是由实现来执行。抽象和实现都可以有它们各自的变化，被多个子类继承，拥有不同的功能。

#### 代码示例

故事背景：城市A和城市B是分隔两岸的两座城市，他们通过一座拥有悠久历史的桥连接起来。城市A人文气息浓厚，商店林立，人来人往，熙熙攘攘。城市B工业气息浓厚，各种机器和手工作坊相辉映，在重复的工序中输出产品。城市A和城市B交互的模式，一般是城市A下订单，城市B完成订单。

抽象化角色（OrderAbstraction.java）：
``` java
// 订单
public abstract class OrderAbstraction {
	protected FactoryImplementor factoryImplementor;
	
	public OrderAbstraction(FactoryImplementor factoryImplementor) {
		this.factoryImplementor = factoryImplementor;
	}
	
	public abstract void provide();
}
```

扩充抽象化角色（CakeOrderRefinedAbstraction.java）：
``` java
// 蛋糕订单
public class CakeOrderRefinedAbstraction extends OrderAbstraction {
	private int count;
	private String orderName = "蛋糕";

	public CakeOrderRefinedAbstraction(int count, FactoryImplementor factoryImplementor) {
		super(factoryImplementor);
		this.count = count;
	}

	@Override
	public void provide() {
		factoryImplementor.provide(count, orderName);
	}

}
// 糖果订单
public class CandyOrderRefinedAbstraction extends OrderAbstraction {
	private int count;
	private String orderName = "糖果";

	public CandyOrderRefinedAbstraction(int count, FactoryImplementor factoryImplementor) {
		super(factoryImplementor);
		this.count = count;
	}

	@Override
	public void provide() {
		factoryImplementor.provide(count, orderName);
	}

}
```

实现化角色（FactoryImplementor.java）：
``` java
// 工厂
public interface FactoryImplementor {
	public void provide(int count, String orderName);
}
```

具体实现化角色（HandworkFactoryConcreteImplementor.java、MachineFactoryConcreteImplementor.java）：
``` java
// 手工作坊
public class HandworkFactoryConcreteImplementor implements FactoryImplementor {

	public void provide(int count, String orderName) {
		float time = (float)(count * 1);
		System.out.println("手工使用了" + time + "小时，完成了" + count + "份" + orderName);
	}

}
// 工厂机器
public class MachineFactoryConcreteImplementor implements FactoryImplementor {

	public void provide(int count, String orderName) {
		float time = (float)(count * 0.5);
		System.out.println("机器使用了" + time + "小时，完成了" + count + "份" + orderName);
	}

}
```

测试类（BridgePatternTest.java）：
``` java
public class BridgePatternTest {

	public static void main(String[] args) {
		// 手工蛋糕订单
		OrderAbstraction handworkCake = 
				new CakeOrderRefinedAbstraction(10, 
						new HandworkFactoryConcreteImplementor());
		// 机器蛋糕订单
		OrderAbstraction machineCake = 
				new CakeOrderRefinedAbstraction(10, 
						new MachineFactoryConcreteImplementor());
		// 手工糖果订单
		OrderAbstraction handworkCandy = 
				new CandyOrderRefinedAbstraction(10, 
						new HandworkFactoryConcreteImplementor());
		// 机器糖果订单
		OrderAbstraction machineCandy = 
				new CandyOrderRefinedAbstraction(10, 
						new MachineFactoryConcreteImplementor());

		handworkCake.provide();
		machineCake.provide();
		handworkCandy.provide();
		machineCandy.provide();
	}
}
```

测试结果：
![桥接模式测试结果](https://raw.githubusercontent.com/Mingmingcome/cnblogs/master/images/bridge-pattern-result.png)

结果表明了：抽象和实现分离，各自变化。订单存在着两个维度变化，一个是种类，一个是制造方式，种类分为蛋糕和糖果，制造方式有手工和机器。
#### 其他实例

手机软件也有种类和运行在不同的操作系统上两个维度的变化，种类有通讯录、短信等，操作系统IOS，Android等，这样就适用于桥接模式。

如果需要开发一个跨平台视频播放器，可以在不同操作系统平台（如Windows、Linux、Unix等）上播放多种格式的视频文件，常见的视频格式包括MPEG、RMVB、AVI、WMV等。同样适用于桥接模式。

#### 与适配器模式的区别

桥接模式是在设计之初，认为抽象和现实都存在多维的变化，而且抽象和实现的变化是不相关的，在可预见的情况下做出的选择，适配器模式是系统已经成熟到无法修改或者修改的工作量无法估量的时候，但是又想复用当前的功能的时候使用，或者使用第三方组件的时候使用。很多情况下，桥接模式就是使用了对象适配器模式实现的。适配器模式可以参考本人[适配者模式](https://www.cnblogs.com/mingmingcome/p/9810731.html)。

#### 优点

- 1、抽象和实现分离

- 2、多维度变化

#### 缺点

- 1、增加系统的设计和理解难度

#### 总结

当在系统设计初期，发现抽象和实现存在各自的变化或某个类由两个不相关的维度描述时，可以考虑桥接模式。

#### 完

`2018年11月16日21:56:11`