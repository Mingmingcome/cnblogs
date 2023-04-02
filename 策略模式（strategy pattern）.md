`2020年9月6日16:39:34`

## 策略模式（strategy pattern）

#### 定义

>自古深情留不住，唯有套路得人心。

<i>Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it。</i>

定义一系列算法，将每一个算法封装起来，并让它们可以相互替换。策略模式让算法独立于使用它的客户而变化。——《设计模式：可复用面向对象软件的基础》

#### 图示

策略模式结构图：

![策略模式结构图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200906100547strategy-pattern-structure-diagram.jpg)

#### 角色

策略模式有三个角色，分别是：

- 抽象策略角色（Strategy）：是所有具体策略类共同的接口
- 具体策略角色（ConcreteStrategy）：是实现了抽象策略接口的具体实现类
- 上下文角色（Context）：拥有Strategy的引用，提供具体策略。

#### 代码示例

声明：以下故事纯属虚构，不代表我自己的观点，无歧视任何人。

这个故事要从渣男说起。有天在看新闻时，看到《渣男同时交17个女友：看人家这秘籍！》，我母胎单身，推荐精准啊，匆忙点击进去看到了渣男的秘籍。过了一会我就退出了，我深知自己做不到这样子，谎话连篇，都是套路。今天写策略模式时，忽然想到了这个。

渣男有一个葵花宝典，面对不同的女性，使用不同的套路，只为得到女人的心。面对拜金女，显示自己的有钱，买金卖银。面对爱才女，写诗写词。

代码示例类图：

![策略模式类图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200906122116strategy-pattern-class-diagram.jpg) 

抽象策略角色（TaoLu.java）:
```java
// 套路
public interface TaoLu {
    void taoLu();
}
```

具体策略角色（RichTaoLu.java、TalentedTaoLu.java）：
```java
// 有钱人套路
public class RichTaoLu implements TaoLu {
    @Override
    public void taoLu() {
        System.out.println("为她买金");
        System.out.println("为她买银");
        System.out.println("为她买包包");
    }
}

// 有才套路
public class TalentedTaoLu implements TaoLu {
    @Override
    public void taoLu() {
        System.out.println("为她写诗");
        System.out.println("为她弹琴写词");
        System.out.println("为她摘星星月亮");
    }
}
```

上下文角色（KuiHuaBaoDian.java）：
```java
// 葵花宝典
public class KuiHuaBaoDian {
    // 持有套路（Strategy）的引用
    private TaoLu taoLu;

    public KuiHuaBaoDian(TaoLu taoLu) {
        this.taoLu = taoLu;
    }

    public void toGetTheGirlIntoBed(String girl) {
        taoLu.taoLu();
        System.out.println("你得到了" + girl + "的心");
    }
}
```

测试类（ZhaNan.java）：
```java
// 渣男
public class ZhaNan {
    public static void main(String[] args) {
        KuiHuaBaoDian kuiHuaBaoDian;

        String girl1 = "拜金女";
        // 选择策略
        TaoLu taoLu1 = new RichTaoLu();
        // 创建上下文
        kuiHuaBaoDian = new KuiHuaBaoDian(taoLu1);
        kuiHuaBaoDian.toGetTheGirlIntoBed(girl1);

        String girl2 = "爱才女";
        // 选择策略
        TaoLu taoLu2 = new TalentedTaoLu();
        // 创建上下文
        kuiHuaBaoDian = new KuiHuaBaoDian(taoLu2);
        kuiHuaBaoDian.toGetTheGirlIntoBed(girl2);
    }
}
```

测试结果如下：
![渣男获得多个女朋友](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200906123239strategy-pattern-result.jpg)

#### 使用场景

策略模式使用场景有：

- 在一个系统里面有许多类，它们之间的区别仅在于它们的行为，使用策略模式可以动态地让一个对象在许多行为中选择一种行为；（追女孩子可以选择有钱或者有才）
- 一个系统需要动态地在几种算法中选择一种；
- 避免使用难以维护的多重条件选择语句；
- 希望在具体策略类中封装算法和与相关的数据结构（有钱可以买金卖银，有才可以写诗写词）

在[strategy pattern](https://springframework.guru/gang-of-four-design-patterns/strategy-pattern/)文章中提到：

<i>There is a lot of debate around the use of the Strategy Pattern with Spring. Often you’ll see the Strategy Pattern used in conjunction with Dependency Injection, where Springs IoC container is making the choice of which strategy to use. Different data sources as a great example. Using a H2 data source for local development is one strategy. Using MySQL for production is another strategy. Which one is to use at runtime is up to the Spring IoC container.</i>

在Spring中的策略模式：

- 依赖注入时，Spring IoC容器会应用策略模式选择使用哪种策略。
- 还有Spring IoC容器在运行时决定在开发环境使用H2数据源，在生产环境MySQL。


#### 优点

- 符合开闭原则，增加策略只需要增加具体策略实现类即可
- 避免（if-else if-else)多重选择语句
- 封装了具体算法，客户端不需要知道具体算法

#### 缺点

- 客户端需要知道使用哪种具体策略（对不同的女性采取不同的策略）
- 策略模式会产生很多策略类，多后期学习维护增加一定难度

#### 总结

策略模式封装算法，提供给上下文选择使用，客户端无需关心算法具体逻辑，无需关系关系算法更替，但是使用前需要知道具体策略类。

#### 完

`2020年9月6日21:04:51`