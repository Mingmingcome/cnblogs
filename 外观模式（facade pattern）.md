`2019年3月27日09:03:40`

## 外观模式（ facade pattern）

#### 定义

>纵然身体里每个细胞都复杂得仿佛是一个宇宙，但是给人第一印象的你帅气的脸。

外观模式，为子系统中的一组接口提供一个一致的界面，此模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。————《设计模式：可复用面向对象软件的基础》

外观模式是一种对象结构型模式。

#### 使用场景

- 1、为了使子系统简单易用，为子系统的一组接口提供一个简单的接口

- 2、最小化对子系统的依赖性

- 3、构建层次化的子系统的时候，使用外观模式定义系统每一层的入口

简单明了的例子就是家里的开关系统，灯、空调、冰箱等有自己的开关，而外观模式定义的就是总开关，你通过调用总开关就调用所有子系统即所有开关。

#### 角色

客户角色（Client）：调用外观角色

外观角色（Facade）：知道哪些子系统负责处理请求，将客户端的请求转发给适当的子系统对象。

子系统类角色（Subsystem Classes）：实现子系统的功能，处理外观角色指派的任务。

#### 图示

当一个系统拥有复杂的子系统时：

![没有应用外观模式的系统](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_facade-without-facade.jpg)

不同的客户端调用了不同子系统类的时候，如第一个客户端调用了Class1和Class2，第二个客户端调用了Class1、Class2、Class3等，导致耦合紧密，对于开发来说不利于修改，对于用户来说不利于使用。

当使用了外观模式之后：

![应用外观模式的系统](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_facade-with-facade.jpg)

有了Facade外观类这个高层接口，耦合降低，开发不用一个个去问用户：“你们有没有使用到Class1这个类”，等到所有用户都确认了之后才开始动手，本来可能是几行代码的工作量，但是这个询问的过程都得好几天，谁用谁知道。对于用户来说也不需要关注子系统类是否修改，如果功能不变的话还是使用之前的接口方法，如果功能需要改变且现有的接口方法无法满足那只有提需求添加接口方法了。

#### 代码示例

例子：这里有一台计算机，开机需要CPU、硬盘、内存的配合，但是用户不需要知道CPU是否运行、硬盘是否被读取、内存是否被加载，计算机应用外观模式定义了一个开机键，用户只需要按了开机键就可以完成开机了。

CPU类：

``` java
public class CPU {
    public void freeze() {
        System.out.println("CPU执行freeze操作");
    }
    public void jump() {
        System.out.println("CPU执行jump操作");
    }
    public void execute() {
        System.out.println("CPU正常运行");
    }
}
```

硬盘类：

``` java
public class HardDrive {
    public void read() {
        System.out.println("读取硬盘");
    }
}
```

内存类：

``` java
public class Memory {
    public void load() {
        System.out.println("将硬盘中读取到信息加载到运行内存");
    }
}
```

外观类：

``` java
public class ComputerFacade {
    private CPU processor;
    private Memory ram;
    private HardDrive hd;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        hd.read();
        ram.load();
        processor.jump();
        processor.execute();
        System.out.println("计算机正常启动完毕");
    }
}
```

start方法就是相当于开机键。

测试类：

``` java
public class User {
    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.start();
    }
}
```

测试结果：

![测试结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_facade-example-result.png)

结果显示只需要调用外观类的start方法就可以启动计算机。

#### 模式扩展

- <b>一个系统可以有多个外观类</b>

在一个系统中可以设计多个外观类，每个外观类都负责和一些特定的子系统交互，向用户提供相应的业务功能

- <b>不要试图通过外观类为子系统增加新行为</b>

这个装饰者模式所做的事情，为某个对象动态增加新的行为。

- <b>外观模式与迪米特法则</b>

外观模式是迪米特法则的践行者，遵循着让客户端知道最少的原则，实现客户端和子系统类的解耦

- <b>抽象外观类的引入</b>

外观模式不符合“开闭模式”，当子系统类增加或者减少的时候，都需要修改外观类中的方法。引入抽象外观类在一定程度上解决了这个问题，但是维护抽象外观类及其子类也需要一定的成本。


#### 优点

1、解耦。降低客户端与子系统类耦合性，增加和删除子系统类只需要修改外观类即可。

#### 缺点

1、不符合开闭原则。

#### 总结

外观模式适用于客户端与子系统的多个接口直接关联，关系错综复杂。子系统增加外观类，提供一个简单的接口给客户端调用，降低客户端与子系统的耦合性，有利于子系统更新或迁移。

#### 完

`2019年5月6日23:57:41`