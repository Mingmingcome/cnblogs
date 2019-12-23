`2019年7月19日20:23:27`

## 原型模式（prototype pattern）

#### 定义

>在平行时空理论中，每个平行时空都有一个你，总有一个你选对了路，在茫茫的恒河沙数的宇宙里，总有一个你，终生幸福。

原型模式，用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。————《设计模式：可复用面向对象软件的基础》

原型模式是一种对象创建型模式。

#### 使用场景

原型是真正对象实例被创建之前的任意对象的模板。原型模式在以下场景中使用：当应用需要创建一个类的大量实例，并且这些实例都拥有同样的状态或者只有很少的不同。

在原型设计模式中，一个真正对象实例在开始的时候被创建，然后无论什么时候需要创建一个新对象实例，原型通过clone创建另外一个实例。原始模式的主要好处是最小化创建实例过程的消耗，通过new来创建一个对象实例的过程的消耗要比通过clone方法创建对象实例的过程的消耗大的多。

#### 图示

原型模式结构图：

![原型模式结构图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_prototype-pattern-structure-diagram.jpg)

#### 角色

原型模式简单明了，分别是：

- （1）客户端（Client）角色：客户端提出创建对象的请求。

- （2）抽象原型（Prototype）角色：这是一个抽象角色，通常是Java接口和Java抽象类。此角色给出所有的具体原型类（Concrete Prototype）的接口。在Java中通常是Cloneable接口。

- （3）具体原型（Concrete Prototype）角色：被复制的对象。此角色需要实现抽象原型角色的接口。

#### 代码示例

创建一个娱乐应用，它需要频繁创建Movie，Album和Show类实例。我会首先创建他们的原型类实例，当每次需要创建对象实例时，通过原型clone实现。

java应用原型模式方法：实现Cloneable接口。

类图：

![原型设计模式类图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_prototype-pattern-class-diagram.jpg)

抽象原型角色：

``` java
public interface PrototypeCapable extends Cloneable {
    PrototypeCapable clone() throws CloneNotSupportedException;
}

```

具体原型角色：

``` java
public class Movie implements PrototypeCapable {

    private String name = "钢铁侠";

    @Override
    public Movie clone() throws CloneNotSupportedException {
        System.out.println("拷贝Movie对象");
        return (Movie)super.clone();
    }

    @Override
    public String toString() {
        return "Movie{name='" + name + "'}";
    }
}

public class Album implements PrototypeCapable {

    private String name = "无法长大";

    @Override
    public Album clone() throws CloneNotSupportedException {
        System.out.println("拷贝Album对象");
        return (Album)super.clone();
    }

    @Override
    public String toString() {
        return "Album{name='" + name + "'}";
    }
}

public class Show implements PrototypeCapable {

    private String name = "维多利亚的秘密";

    @Override
    public Show clone() throws CloneNotSupportedException {
        System.out.println("拷贝Show对象实例");
        return (Show)super.clone();
    }

    @Override
    public String toString() {
        return "Show{name='" + name + "'}";
    }
}
```

客户端角色：

``` java
public class PrototypePatternTest{
    public static void main(String[] args) throws CloneNotSupportedException {
        Movie moviePrototype = new Movie();
        Movie movie = moviePrototype.clone();
        System.out.println(movie);

        Album albumPrototype = new Album();
        Album album = albumPrototype.clone();
        System.out.println(album);

        Show showPrototype = new Show();
        Show show = showPrototype.clone();
        System.out.println(show);
    }
}
```
测试结果：

![原型模式测试结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618458/o_prototype-pattern-result.jpg)

#### 优点

使用原型模式创建对象对直接new一个对象在性能上要好得多，因为Object类的clone方法是一个本地方法，它直接操作内存中的二进制流，特别是复制大对象时，性能的差别比较明显。

#### 总结

原型模式是通过拷贝原型对象实例来创建新的对象实例。

#### 参考

[Prototype design pattern in Java](https://howtodoinjava.com/design-patterns/creational/prototype-design-pattern-in-java/)

#### 完