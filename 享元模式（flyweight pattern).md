`2019年5月14日22:13:58`

## 享元模式（flyweight pattern）

#### 定义

享元模式（Flyweight），运用共享技术有效地支持大量细粒度的对象。——《设计模式：可复用面向对象软件的基础》

>Flyweight在拳击比赛中只最轻量级，即“蝇量即”和“雨量级”，这里使用“享元模式”的意译，是因为这样更能反映模式的用意。——《JAVA与模式》

享元模式是对象结构型模式之一，此模式通过减少对象的数量，从而改善应用程序所需的对象结构。

#### 使用场景

我们在需要创建大量（例如10^5）的相似的对象时，使用享元模式。反正我从来没有需要创建这么多相似对象的时候，享元模式在真正的应用中用的要比较少，一般是一些底层数据结构使用到。比如，Java中的String。

``` java
public class StringDemo {

    public static void main(String[] args) {
        String a = "abc";
        String b = "abc";

        System.out.println(a == b); // true

    }
}
```

上面的例子中控制台打印为：true，

