`begin 2020年11月14日20:19:59`
## 状态模式(state pattern)

#### 引子

铁扇公主：以前陪我看月亮的时候，叫人家小甜甜，现在新人胜旧人了，叫人家牛夫人！

#### 定义

<i>Allow an object to alter its behavior when its internal state changes.The object will appear to change its class. </i>

当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类。 ——《设计模式：可复用面向对象软件的基础》

状态模式是一个行为型设计模式。

#### 概述

状态模式的概念和有限状态机（finite-state machine）相似。有限状态机：表示有限个状态以及在这些状态之间的转移和动作等行为的数学计算模型。

如TCP连接几个固定的状态（部分状态省略）：Established，Listening，Closed。

Closed表示初始状态；Listening表示服务器端的某个SOCKET处于监听状态，可以接受连接；Established表示连接已经建立了。

TCP也有几个固定的行为（部分行为省略）：open，close，synchronize，acknowledge。

open表示开启连接，close表示关闭连接，synchronize表示同步，acknowledge表示确认。

每个行为根据当前状态的不一样，行为表现也是不一样的，有的是有效行为，有的是无效行为。如果当前状态是closed状态，open为有效行为，其他为无效行为。如果当前状态是Listening状态，synchronize，acknowledge为有效行为，其他为无效行为。如果当前状态为established状态，synchronize，acknowledge，close为有效行为，其他为无效行为。

如果我现在有TCPConnection类，接收请求后需要表现什么行为，那么可能是着这样子：

```java
public class TCPConnection {
    private String state;

    void open() {
        switch (state) {
            case "closed":
                // open创建连接
                state = "established";
                break;
            case "established":
            case "listening":
                // 无效行为
                break;
        }

    }

    void close() {
        switch (state) {
            case "closed":
                // 无效行为
                break;
            case "established":
            case "listening":
                // close关闭连接
                break;
        }
    }

    // ...（省略）

}
```

状态模式将状态抽象出来，封装状态相对应的操作，当状态转换时，操作也跟着转换。每个具体状态只需要一个，具体状态类提供

```java
// TCP连接
public class TCPConnectionUseStatePattern {
    private TCPState tcpState;
    private ClosedState closedState;
    private EstablishedState establishedState;
    
    TCPConnectionUseStatePattern() {
        closedState = new ClosedState();
        establishedState = new EstablishedState();
        tcpState = closedState;
    }

    void open() {
        tcpState.open(this);
    }

    void close() {
        tcpState.close(this);
    }

    void changeState(TCPState tcpState) {
        this.tcpState = tcpState;
    }

    public ClosedState getClosedState() {
        return closedState;
    }

    public EstablishedState getEstablishedState() {
        return establishedState;
    }
}
// TCP状态
public interface TCPState {
    void open(TCPConnectionUseStatePattern tcpConnectionUseStatePattern);
    void close(TCPConnectionUseStatePattern tcpConnectionUseStatePattern);
    // ...（省略）
}
// 关闭状态
public class ClosedState implements TCPState {
    @Override
    public void open(TCPConnectionUseStatePattern tcpConnectionUseStatePattern) {
        System.out.println("创建连接");
        System.out.println("更新状态");
        tcpConnectionUseStatePattern.changeState(tcpConnectionUseStatePattern.getEstablishedState());
    }

    @Override
    public void close(TCPConnectionUseStatePattern tcpConnectionUseStatePattern) {
        System.out.println("已关闭，无需重复关闭");
    }
}
// 已连接状态
public class EstablishedState implements TCPState {
    @Override
    public void open(TCPConnectionUseStatePattern tcpConnectionUseStatePattern) {
        System.out.println("已连接，无需重复连接");
    }

    @Override
    public void close(TCPConnectionUseStatePattern tcpConnectionUseStatePattern) {
        System.out.println("关闭连接");
        System.out.println("更新状态");
        tcpConnectionUseStatePattern.changeState(tcpConnectionUseStatePattern.getClosedState());
    }
}
```

#### 图示

状态模式结构图：

![state-pattern-structure-diagram](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_201115085403state-pattern-structure-diagram.png)

#### 角色

<b>上下文角色（Context）</b>:

- 定义客户端使用的接口方法
- 维护一个具体状态类实例，定义当前状态

<b>抽象状态类（State）</b>：定义封装了与Context状态相关的行为的一组接口

<b>具体状态类（Concrete State）</b>：实现State，实现与Context状态相关的具体行为

#### 代码示例

假设有一个自动售卖机，需要投币，按按钮，饮料就会滚出来。

自动售卖机有四个状态，分别是：没有硬币没有饮料（No Coin，No Drinks）、有硬币没有饮料（Has Coin，No Drinks）、有硬币有饮料（Has Coin And Drinks）、没有硬币有饮料（No Coin，Has Drinks），有是两个行为：投币（Insert Coin）、按按钮（Press Button）、添加饮料（Add Drinks）。

![状态模式示例](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_201121125555state-pattern-example-diagram.png)

图中部分行为未画全，画出了正常的流程，有饮料时投币按按钮分派饮料（没有硬币有饮料<->有硬币有饮料），无饮料时投币按按钮返还硬币（没有饮料没有硬币<->有硬币没有饮料）。还有其他状态的行为，如在有硬币有饮料的时候，在投币，会拒绝，直接返还硬币等。

对应不同的状态，封装了不同的行为，当改变状态时，相当于改变了其行为。有限个状态，并且彼此知道彼此的存在。

talk is cheap, show you code.

<b>上下文角色（Context）:</b>
```java
// 饮料自动售卖机
public class DrinksVendingMachine {
    private DrinksVendingMachineState state;
    private NoCoinHasDrinks noCoinHasDrinks;
    private NoCoinNoDrinks noCoinNoDrinks;
    private HasCoinAndDrinks hasCoinAndDrinks;
    private HasCoinNoDrinks hasCoinNoDrinks;
    private int size = 0; // 当前饮料数
    private int capacity = 3; // 最大饮料数

    public DrinksVendingMachine() {
        this.noCoinHasDrinks = new NoCoinHasDrinks();
        this.noCoinNoDrinks = new NoCoinNoDrinks();
        this.hasCoinAndDrinks = new HasCoinAndDrinks();
        this.hasCoinNoDrinks = new HasCoinNoDrinks();
        this.state = noCoinNoDrinks;
    }

    public void insertCoin() {
        state.insertCoin(this);
    }

    public void pressButton() {
        state.pressButton(this);
    }

    public void addDrinks() {
        state.addDrinks(this, capacity - size); // 加满
    }

    public void changeState(DrinksVendingMachineState state) {
        this.state = state;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public NoCoinHasDrinks getNoCoinHasDrinks() {
        return noCoinHasDrinks;
    }

    public NoCoinNoDrinks getNoCoinNoDrinks() {
        return noCoinNoDrinks;
    }

    public HasCoinAndDrinks getHasCoinAndDrinks() {
        return hasCoinAndDrinks;
    }

    public HasCoinNoDrinks getHasCoinNoDrinks() {
        return hasCoinNoDrinks;
    }
}
```

<b>抽象状态类（State）:</b>
```java
// 自动售卖机状态
public interface DrinksVendingMachineState {
    void insertCoin(DrinksVendingMachine drinksVendingMachine);
    void pressButton(DrinksVendingMachine drinksVendingMachine);
    void addDrinks(DrinksVendingMachine drinksVendingMachine, int num);
}
```

<b>具体抽象类（ConcreteState）：</b>
```java
// 有硬币有饮料
public class HasCoinAndDrinks implements DrinksVendingMachineState {
    @Override
    public void insertCoin(DrinksVendingMachine drinksVendingMachine) {
        System.out.println("已有硬币，请取走你的硬币并按按钮，谢谢！");
    }

    @Override
    public void pressButton(DrinksVendingMachine drinksVendingMachine) {
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() - 1);
        if (drinksVendingMachine.isEmpty()) {
            drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinNoDrinks());
        } else {
            drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinHasDrinks());
        }
        System.out.println("请取走您的饮料，谢谢！");
    }

    @Override
    public void addDrinks(DrinksVendingMachine drinksVendingMachine, int num) {
        if (drinksVendingMachine.isFull()) {
            System.out.println("自动售卖机饮料已满，等待卖出后再添加！");
        }
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() + num);
        drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinHasDrinks());
        System.out.println("添加饮料成功！");
    }
}
// 有硬币没有饮料
public class HasCoinNoDrinks implements DrinksVendingMachineState {
    @Override
    public void insertCoin(DrinksVendingMachine drinksVendingMachine) {
        System.out.println("已有硬币，请取走你的硬币并按按钮，谢谢！");
    }

    @Override
    public void pressButton(DrinksVendingMachine drinksVendingMachine) {
        drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinNoDrinks());
        System.out.println("暂时无饮料，请取走您的硬币，谢谢！");
    }

    @Override
    public void addDrinks(DrinksVendingMachine drinksVendingMachine, int num) {
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() + num);
        drinksVendingMachine.changeState(drinksVendingMachine.getHasCoinAndDrinks());
        System.out.println("添加饮料成功！");
    }
}
// 没有硬币有饮料
public class NoCoinHasDrinks implements DrinksVendingMachineState {
    @Override
    public void insertCoin(DrinksVendingMachine drinksVendingMachine) {
        drinksVendingMachine.changeState(drinksVendingMachine.getHasCoinAndDrinks());
        System.out.println("投币成功，请按按钮！");
    }

    @Override
    public void pressButton(DrinksVendingMachine drinksVendingMachine) {
        System.out.println("请先投币，谢谢！");
    }

    @Override
    public void addDrinks(DrinksVendingMachine drinksVendingMachine, int num) {
        if (drinksVendingMachine.isFull()) {
            System.out.println("自动售卖机饮料已满，等待卖出后再添加！");
        }
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() + num);
        drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinHasDrinks());
        System.out.println("添加饮料成功！");
    }
}
// 没有硬币没有饮料
public class NoCoinNoDrinks implements DrinksVendingMachineState {
    @Override
    public void insertCoin(DrinksVendingMachine drinksVendingMachine) {
        drinksVendingMachine.changeState(drinksVendingMachine.getHasCoinNoDrinks());
        System.out.println("投币成功，请按按钮!");
    }

    @Override
    public void pressButton(DrinksVendingMachine drinksVendingMachine) {
        System.out.println("饮料已空，请联系工作人员添加饮料！");
    }

    @Override
    public void addDrinks(DrinksVendingMachine drinksVendingMachine, int num) {
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() + num);
        drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinHasDrinks());
        System.out.println("添加饮料成功！");
    }
}
```

测试类（StatePatternTest）：
```java
public class StatePatternTest {
    public static void main(String[] args) {
        // 1.初始化状态：NoCoinNoDrinks
        DrinksVendingMachine drinksVendingMachine = new DrinksVendingMachine();
        // 无效行为，不会改变状态
        drinksVendingMachine.pressButton();
        // 2.状态：NoCoinNoDrinks -> HasCoinNoDrinks
        drinksVendingMachine.insertCoin();
        drinksVendingMachine.insertCoin();
        // 3.状态：HasCoinNoDrinks -> NoCoinNoDrinks
        drinksVendingMachine.pressButton();
        // 4.状态：NoCoinNoDrinks -> NoCoinHasDrinks
        drinksVendingMachine.addDrinks();
        // 5、状态：(NoCoinHasDrinks <-> HasCoinAndDrinks){多次} -> NoCoinNoDrinks
        while (drinksVendingMachine.getSize() > 0) {
            drinksVendingMachine.insertCoin();
            drinksVendingMachine.insertCoin();
            drinksVendingMachine.pressButton();
            drinksVendingMachine.pressButton();
        }
    }
}
```

测试结果截图：
![状态模式测试结果图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_201122065609state-example-test-result.jpg)

#### 使用场景

当遇到下面任意一种场景都可以使用状态模式：

- 一个对象的行为由它的状态决定，而且它必须在运行时根据自身状态改变它的行为。如示例中，自动售卖机状态可以在运行时从<b>有硬币有饮料</b>变成<b>没有硬币没有饮料</b>
- 代码中包含大量与对象状态有关的条件语句，这些条件语句的出现，会导致代码的可维护性和灵活性变差，不能方便地增加和删除状态，使客户类与类库之间的耦合增强。在这些条件语句中包含了对象的行为，而且这些条件对应于对象的各种状态。如概述里面提供，TCP连接通过Switch语句来判断执行什么行为，不好维护。

#### 与策略模式对比

如果状态模式提供外部接口，使得其他对象可以知道Context包含的状态，且能改变Context的状态，状态模式就变成了一个策略模式，每种状态就是一种策略。

但是状态模式的状态一般是不变的，策略模式是可以随意添加策略的，而不影响其他策略。

#### 优点

- 符合设计模式六大原则中的单一职责原则
- 封装与状态的行为到一个类中，降低耦合，增加可维护性

#### 缺点

- 增加系统类个数，增加系统复杂性

#### 总结

状态模式是一个行为型设计模式，角色有三个：上下文角色、抽象状态类、具体状态类。

上下文角色定义客户端使用的接口方法，维护一个具体状态类实例，定义当前状态。抽象状态类定义封装了与上下文角色相关的行为的一组接口，具体状态类则实现不同的状态对应的相同行为的不同表现。

状态模式符合单一职责规则，降低耦合，提高可维护性。

#### 参考

[1]https://springframework.guru/gang-of-four-design-patterns/state-pattern/

[2]https://refactoring.guru/design-patterns/state

[3]https://en.wikipedia.org/wiki/State_pattern

#### 完

`end 2020年11月22日16:14:18`
