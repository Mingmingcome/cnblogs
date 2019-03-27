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

![没有应用外观模式的系统](https://raw.githubusercontent.com/Mingmingcome/cnblogs/master/images/facade-without-facade.jpg)

当使用了外观模式之后：

![应用外观模式的系统](https://raw.githubusercontent.com/Mingmingcome/cnblogs/master/images/facade-with-facade.jpg)

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

![测试结果](https://raw.githubusercontent.com/Mingmingcome/cnblogs/master/images/facade-example-result.png)

结果显示只需要调用外观类的start方法就可以启动计算机。

#### 模式扩展

#### 优点



#### 缺点



#### 总结

#### 参考

#### 完