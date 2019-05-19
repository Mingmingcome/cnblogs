`2019年5月14日22:13:58`

## 享元模式（flyweight pattern）

#### 定义

享元模式（Flyweight），运用共享技术有效地支持大量细粒度的对象。——《设计模式：可复用面向对象软件的基础》

>Flyweight在拳击比赛中只最轻量级，即“蝇量即”和“雨量级”，这里使用“享元模式”的意译，是因为这样更能反映模式的用意。——《JAVA与模式》

享元模式是对象结构型模式之一，此模式通过减少对象的数量，从而改善应用程序所需的对象结构。

#### 使用场景

我们在需要创建大量（例如10^5）的相似的对象时，使用享元模式。反正我从来没有需要创建这么多相似对象的时候，享元模式在真正的应用中用的要比较少，一般是一些底层数据结构使用到。比如，Java中的String。有图：

![IDEA String类实例](https://raw.githubusercontent.com/Mingmingcome/cnblogs/master/images/flyweight-string.png)

图中的String类实例数就达到了20多万，所以String使用了享元模式。再看看大小，char[]和String对比，差了一个数量级。按道理来说，char[]和String的大小应该是差不多的啊，为什么呢？我们再看看源码：

``` java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];

    //省略
}
```
String中的char[]就是用来存储字符串的，然后String只是保存着char[]的引用（即这个数组的内存地址）。所以char[]和String数量差不多，但是大小却差了一个数量级的原因是char[]存储着真正的字符串内容，String只是存储着char[]引用。而且这个char[]放在常量池中，通过享元模式被String引用，这样子一个char[]就可能被多个String共享引用。多说无益，show me code。

``` java
public class StringDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String a = "mingmingcome";
        String b = "mingmingcome";

        System.out.println(a == b); // true
        System.out.println(a); // mingmingcome

        String c = new String("mingmingcome");
        String d = new String("mingmingcome");

        System.out.println(c.equals(d));

        // 利用反射修改String中私有成员变量char[]
        Field value = c.getClass().getDeclaredField("value");
        value.setAccessible(true);
        char[] o = (char[])value.get(c);
        o[0] = 'L';
        o[1] = 'O';
        o[2] = 'V';
        o[3] = 'E';

        System.out.println(a); //LOVEmingcome
        System.out.println(b); //LOVEmingcome
        System.out.println(c); //LOVEmingcome
        System.out.println(d); //LOVEmingcome

    }
}
```

上面的例子中控制台打印为：

```
true
mingmingcome
true
LOVEmingcome
LOVEmingcome
LOVEmingcome
LOVEmingcome
```

第一个输出：true说明`a`和`b`是同一个对象，那`a`和`b`也共享了同一个char[]。

第二个输出：mingmingcome是`a`改变前的字符串。

第三个输出：true，是new出来的String实例对象，equals为true，说明char[]相等。

在我们后面利用反射修改`c`中私有成员变量char[]，`a`、`b`、`c`、`d`打印输出都为LOVEmingcome，充分说明`a`、`b`、`c`、`d`就是使用了享元模式共享了同一个char[]。

#### 角色

<b>享元工厂（Flyweight Factory）</b>：一个享元工厂，用来创建并管理Flyweight对象。它主要是用来确保合理地共享Flyweight，当用户请求一个Flyweight是，FlyweightFactory对象提供一个已创建的实例或者创建一个（如果不存在的话）。J

<b>抽象享元（Flyweight）</b>：所有具体享元的超类和接口，通过这个接口，Flyweight可以接受并作用于外部状态。

<b>具体享元（Concrete Flyweight）</b>：继承Flyweight超类或实现Flyweight接口，并为内部状态增加存储空间。

<b>非共享具体享元（Unshared Concrete Flyweight）</b>：指那些不需要共享的Flyweight子类。因为Flyweight接口共享成为可能，但他并不强制共享。

#### 内部状态和外部状态

<b>内部状态（intrinsic state）</b>：可以用来共享的不变状态，在具体享元模式中

<b>外部状态（extrinsic state）</b>：可以作为参数传进来的可变状态

为了搞清楚内部状态和外部状态，我们来看一个例子。

假设我们在文本编辑器中输入一个字符，一个Character对象被创建，Character类的属性有名字、字体、大小{name,font,size}。我们不需要当客户端每次输入一个字符时都创建一个对象，因为字符'B'和另外一个字符'B'没有什么不一样。如果客户端再一次输入'B'，我们只需要简单地返回我们之前创建的那个字符'B'对象即可。现在所有这些状态{name,font,size}都是内部状态，因为它们可以在不同的对象中共享。

我们Character类给添加更多的属性：行和列，它们说明字符在文本中的位置。这些属性对于相同的字符来说，也是不同的，因为没有两个character会在文本中有相同的位置。这些属性被称为外部状态，它们是不能在对象中共享的。

#### 图示

享元模式结构图：

![享元模式结构图](https://raw.githubusercontent.com/Mingmingcome/cnblogs/master/images/flyweight-pattern-structure.jpg)

#### 代码示例

<b>实现</b>：在反恐精英中恐怖分子和反恐精英的创建。我们有两个类，一个是恐怖分子Terrorist，另一个是反恐精英Counter Terrorist。当玩家要求一个武器weapon，我们分配给他一个武器。任务：恐怖分子负责放置炸弹，反恐精英负责拆除炸弹。

<b>为什么在这个例子中使用享元模式？</b>因为我们需要减少玩家对象的数量，所以使用享元模式。如果我们不使用享元模式，当有n个玩家玩CS，那么我们需要创建n个对象。现在我们只需要创建两个对象即可，一个是恐怖分子，一个是反恐精英，我们可以在需要的时候一次又一次地重用他们。

内部状态：对于两种类型的玩家来说，任务task是一个内部状态，因为对于所有恐怖分子（或者反恐精英）来说，任务总是一样的。

外部状态：因为每个玩家可以携带任何他选择的武器，所以武器weapon是一个外部状态。武器作为参数由客户端传递。

类图如下：

![类图](https://raw.githubusercontent.com/Mingmingcome/cnblogs/master/images/flyweight-pattern-class-diagram.jpg)

玩家接口：

``` java
public interface Player {
    void assignWeapon(String weapon);
}
```

恐怖分子：

``` java
public class Terrorist implements Player{
    private final String task;

    private String weapon;

    public Terrorist() {
        task = "放置炸弹";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "恐怖分子{" +
                "task='" + task + '\'' +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
```

反恐精英：

``` java
public class CounterTerrorist implements Player{
    private final String task;

    private String weapon;

    public CounterTerrorist() {
        task = "放置炸弹";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "反恐精英{" +
                "task='" + task + '\'' +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
```

玩家工厂：

``` java
public class PlayerFactory {
    private static HashMap<String, Player> hm = new HashMap<>();

    public static Player getPlayer(String playerType) {
        Player p = null;

        if (hm.containsKey(playerType)) {
            p = hm.get(playerType);
        } else {
            switch (playerType) {
                case "Terrorist":
                    p = new Terrorist();
                    System.out.println("恐怖分子已创建");
                    break;
                case "CounterTerrorist":
                    p = new CounterTerrorist();
                    System.out.println("反恐精英已创建");
                    break;
                default:
                    System.out.println("无此玩家类型");
            }
            hm.put(playerType, p);
        }
        return p;
    }
}
```

CS客户端：

``` java
public class CounterStrike {
    private static String[] playerType = {"Terrorist", "CounterTerrorist"};

    private static String[] weapon = {"AK-47", "Maverick", "Gut Knife", "Desert Eagle"};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Player p = PlayerFactory.getPlayer(getPlayerType());
            p.assignWeapon(getWeapon());
            System.out.println(p.toString());
        }
    }

    private static String getPlayerType() {
        Random r = new Random();
        int i = r.nextInt(playerType.length);
        return playerType[i];
    }

    private static String getWeapon() {
        Random r = new Random();
        int i = r.nextInt(weapon.length);
        return weapon[i];
    }
}
```


#### 优点

- 1、可以极大地减少内存中对象的数量，使得相同对宁或相似对象在内存中只保存一份。
- 2、外部状态相对独立，而且不会影响其内部状态，从而使得享元对象可以在不同的环境中被共享。

#### 缺点

- 1、使得系统更加复杂，需要分离出内部状态和外部状态。

#### 总结

享元模式使用共享技术有效地支持大量细粒度的对象，减少内存中对象的数量。

享元模式有内部状态和外部状态，内部状态可以共享，外部状态作为参数传入。

#### 完     

`2019年5月19日22:13:48`
