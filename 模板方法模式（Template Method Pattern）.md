`2020年9月13日13:50:39`

## 模板方法模式(Template Method Pattern)

#### 定义(what)

>科比会三步上篮，我会三步上篮

>科比会投篮，我会投篮

>科比会打铁，我会打铁

>科比 = 我

<i>Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template method lets subclasses redefine certain steps of an algorithm without changing the algorithm’s structure.</i> — 《Design Patterns: Elements of Reusable Object-Oriented Software》

定义一个操作中算法的骨架，而将一个步骤延迟到子类。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。 —— 《设计模式：可复用面向对象软件的基础》

模板方法模式是类的行为模式。准备一个抽象类，将部分逻辑以具体方法以及具体构造函数的形式实现，然后声明一些抽象方法来迫使子类实现剩余的逻辑。不同的子类可以以不同的方式实现这些抽象方法，从而对剩余的逻辑有不同的实现。这就是模板方法模式的用意。—— 《Java与模式》

#### 图示

模板方法模式结构图(structure diagram)：

![模板方法模式结构图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200913064742template-method-pattern-structure-diagram.jpg)

AbstractTemplate是抽象类，定义并实现了一个模板方法templateMethod。这个模板方法一般是一个具体方法，它给出了一个顶级逻辑的骨架，而逻辑的组成步骤在相应的抽象操作（step1、step2）中，推迟到子类实现。

#### 角色

<b>抽象模板(Abstract Template)：</b>

- 定义了一个或者多个抽象操作(step1、step2)，推迟到子类实现。
- 定义了一个模板方法(templateMethod)，是一个具体方法，给出了顶级逻辑的架构，顶层逻辑由抽象操作组成。
- 也会有具体方法，这个方法就是公共方法。

<b>具体模板(Concrete Template)：</b>

- 实现抽象模板的抽象操作

#### 代码示例

以篮球三步上篮作为代码示例。首先，从队友传球给你，你<b>接球</b>，<b>运球</b>，<b>一步</b>，<b>两步</b>，<b>上篮</b>。

代码示例类图：

![模板方法代码示例类图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200913083534template-method-pattern-class-diagram.jpg)

<b>抽象模板(Layup.java)</b>：
``` java
// 上篮
public abstract class Layup {

    public final boolean layupTemplate() {
        boolean flag = catchTheBall();
        flag = dribble(flag);
        flag = stride(flag);
        flag = throwOrLayTheBall(flag);
        return score(flag);
    }

    public final boolean catchTheBall() {
        System.out.println("接球");
        return true;
    }

    protected abstract boolean dribble(boolean flag);
    protected abstract boolean stride(boolean flag);
    protected abstract boolean throwOrLayTheBall(boolean flag);

    public final boolean score(boolean flag) {
        if (flag) {
            System.out.println("上篮成功，得分");
            return true;
        } else {
            System.out.println("上篮失败");
            return false;
        }

    }
}
```

方法layupTemplate()是模板方法，其中有公共方法(catchTheBall、score)，有抽象方法(dribble、stride、throwOrLayTheBall)需要推迟到子类实现。

<b>具体模板(KobeLayup.java、RookieLayup.java)：</b>
``` java
// 科比上篮
public class KobeLayup extends Layup {

    @Override
    public boolean dribble(boolean flag) {
        if (!flag) return false;
        System.out.println("科比运球，来个crossover过人");
        return true;
    }

    @Override
    public boolean stride(boolean flag) {
        if (!flag) return false;
        System.out.println("科比三分线处上篮起步，一步，二步");
        return true;
    }

    @Override
    public boolean throwOrLayTheBall(boolean flag) {
        if (!flag) return false;
        System.out.println("科比起飞，手轻轻一挑，球空心入框");
        return true;
    }
}

// 菜鸟上篮
public class RookieLayup extends Layup {

     private Random random = new Random();

    @Override
    public boolean dribble(boolean flag) {
        if (!flag) return false;
        if (random.nextInt(100) > 60) {
            System.out.println("菜鸟运球");
            return true;
        } else {
            System.out.println("菜鸟运球，手滑了，球丢了");
            return false;
        }
    }

    @Override
    public boolean stride(boolean flag) {
        if (!flag) return false;
        if (random.nextInt(100) > 60) {
            System.out.println("菜鸟上篮起步，一步，两步");
            return true;
        } else {
            System.out.println("菜鸟上篮起步，一步，两步，三步，走步了");
            return false;
        }
    }

    @Override
    public boolean throwOrLayTheBall(boolean flag) {
        if (!flag) return false;
        if (random.nextInt(100) > 60) {
            System.out.println("菜鸟瞄准篮筐，手挑一个，球进了");
            return true;
        } else {
            System.out.println("菜鸟瞄准篮筐，手挑一个，太用力了，哐，打铁了");
            return false;
        }
    }
}
```

具体模板实现了抽象模板中抽象方法，不同的具体模板有不同的行为。

<b>测试类：</b>
``` java
public class TemplateMethodTest {
    public static void main(String[] args) {
        Layup layup;

        // 科比上篮
        layup = new KobeLayup();
        layup.layupTemplate();

        System.out.println("--------------------------------------------");
        // 菜鸟上篮
        layup = new RookieLayup();
        boolean flag;
        int i = 1;
        do {
            System.out.println("菜鸟第" + i++ + "次上篮");
            flag = layup.layupTemplate();
        } while (!flag);

    }
}
```

执行结果：
![模板方方法模式测试结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200913083547template-method-pattern-result.jpg)

#### 使用场景(when)

- 模板方法模式解决的问题是使用一种有不同变体的算法。你需要将算法分解为不同的步骤，在不同实现之间有共同点时，在抽象类中实现。另一方面，不同的步骤将在具体类中实现。
- 在不同的类之间有复制粘贴代码(私有函数)
- 当你的大多数类具有相关行为时，你可以使用此模式

实例：Spring JdbcTemplate，将一个公共方法封装起来，设置数据源(setDataSoure)、获取会话(getSession)、关闭会话(closeSession)等，当然具体查询方法也实现了，如果你想重写也是可以的。

#### 优点

- 将公共方法放到抽象模板中，减少重复代码
- 具体模板类易于增加，删除，修复

#### 缺点

- 具体模板增加，系统复杂度增大

#### 总结

模板方法模式是一种行为型模式，有两种角色：

- 抽象模板定义了模板方法，方法中包含了一些步骤（顶层逻辑），这些步骤有的是具体方法（公共方法），有的是抽象方法；
- 具体模板实现了抽象模板中的抽象方法，将变化延迟到子类

#### 参考

[Design Patterns: Template Method](https://medium.com/better-programming/design-patterns-template-method-5400dde7bb72)

#### 完

`2020年9月13日17:22:44`