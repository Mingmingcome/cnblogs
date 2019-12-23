`2019年3月25日11:01:22`

## 观察者模式（ observer pattern)

#### 写在前面的话

正值金三银四跳槽季，设计模式也是常问的问题之一。本人在3月2日的一次面试的二面中，问到设计模式，问到了观察者模式，而且要求写了伪代码。当时我脑子里就第一个想到的就是《大话设计模式》里面的一个例子，就是员工集体开小差，前台妹妹负责在老板回来时通知所有人。当时回答得结结巴巴，写得代码勉勉强强，惊喜的是二面过了。归，温习之。

#### 定义

>世界上有这么一天，当你来到他们的世界，他们成为你终生的订阅者，随你快乐而快乐，随你忧愁而忧愁。

观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。————《设计模式：可复用面向对象软件的基础》

观察者模式是一种对象行为型模式。

#### 使用场景

当一个对象的改变（名词）需要改变（动词）其他对象的时候。

观察者模式可以解决什么问题：

- 1、应该定义对象间一对多的依赖关系，而不使对象紧密耦合。（达到依赖关系，又不紧耦合）

- 2、应该确保一个对象改变时，无限制的依赖对象自动更新。

- 3、应该一个对象可以通知无限制的其他对象。

第2种情况，消息中间件就是实现之一，当一个生产者发送消息过来，无限制的消费者拿到消息自动更新自己。

第3中情况，发布-订阅推模式，微信公众号应该就是其中有代表性的，当有内容更新，主动通知订阅者。

#### 角色

抽象主题（Subject）： 定义了被观察者常用的方法，订阅（attach）、取消订阅（detach）和通知（notify）功能

具体主题（ConcreteSubject）：实现抽象主题定义的方法，通过attach和detach方法维护一个观察者的集合，当自己维护的状态（state）改变时通知（notify）所有观察者

抽象观察者（Observer）：定义更新自己的方法

具体观察者（ConcreteObserver）：实现更新自己的的方法

#### 图示

观察者类图：

![观察者模式类图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_observer-uml-diagram.jpg)

观察者序列图：

![观察者序列图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_observer-sequence-diagram.jpg)


#### 代码示例

代码示例就使用上面说过的那个例子，员工集体开小差，前台小妹负责在老板回来的时候通知所有人。

首先，前台妹妹是具体主题角色，员工是具体观察者角色。其次，前台妹妹维护着观察者集合，在老板回来了这个状态上通知所有观察者；观察者在前台妹妹通知之后执行自己的更新方法，该干嘛干嘛。

抽象主题：

``` java
public interface Observable {
    public void attach(Observer observer);
    public void detach(Observer observer);
    public void notifyObservers();
}
```


前台妹妹（具体主题即被观察者）：

``` java
public class Receptionist implements Observable {
    private String state = "老板出去了";

    private ArrayList<Observer> observers = new ArrayList<>();

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        System.out.println(state);
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update());
    }
}
```

看NBA员工观察者：

``` java
public class NBAObserver implements  Observer{
    @Override
    public void update() {
        System.out.println("正在看NBA直播的关掉NBA直播，工作");
    }
}
```

炒股员工观察者：

``` java
public class StockObserver implements Observer {
    @Override
    public void update() {
        System.out.println("正在炒股的关闭股市面板，工作");
    }
}
```

观察者模式测试类：

``` java
public class ObserverTest {
    public static void main(String[] args) {
        Receptionist mm = new Receptionist();

        Observer nba = new NBAObserver();
        Observer stock = new StockObserver();

        mm.attach(nba);
        mm.attach(stock);
        // mm看到老板回来了
        mm.setState("老板回来了");
        // 通知已经订阅的员工
        mm.notifyObservers();
    }
}
```

测试结果：

![测试结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_observer-test-result.png)

笔记：明明是前台妹妹在观察老板是否回来了，为什么前台妹妹是被观察者呢？

“老板回来了”这个状态是前台妹妹的内部状态，观察者模式是对象之间的关系，看NBA的和炒股的观察者监听前台妹妹的状态，发生变化时更新自己。

#### Java类库中的观察者模式

在java类库中有java.util.Observer和java.util.Observable作为观察者和被观察者，因为作用有限，在Java 9中已经弃用（deprecated）。

#### 优点

- 满足了当一个对象的改变需要改变其他对象这个条件的前提下，实现了松耦合。

- 符合开闭原则，继承抽象主题添加被观察者，继承抽象观察者添加观察者。


#### 缺点

- 如果一个观察目标对象有很多直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。

- 如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。

- 观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。

#### 总结

观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。

#### 参考

《大话设计模式》

#### 完

`2019年3月26日15:02:09`