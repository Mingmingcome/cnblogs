`2019年11月11日08:45:25`

## 中介者模式（mediator pattern）

#### 定义

>从前的日色变得慢  
>
>车，马，邮件都慢 
> 
>一生只够爱一个人  

中介者模式（mediator pattern），用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地互相引用，从而使其耦合松散，而且可以独立地改变它们的交互。————《设计模式：可复用面向对象软件的基础》

中介者模式是一种对象行为型模式。

从木心这首小诗中的“邮件”中，讨论一下中介者模式。

很久很久以前，你和她住在一个很大很大的村子里面，你住在村的东边，她住在村的西边。

那年你才十八，她也正值青春年华，正月十五元宵节，你赏灯之时，她回首处，你一见钟情。

往后的日子里，你每天都到她家送情书。送了99天，你想这不是办法，每天大半天浪费在路上，没时间赚钱。于是你想了一个办法，创办邮局，每天替村东边的人送信件给村西边的人，一举两得。慢慢邮局越来越大，南边的人通过邮局来给北边的人送信件，你找了几个伙计，从南到北，从北到南送信。

多年后，你富甲一方，也娶了当年的她。

“邮局”就是中介者模式中的中介者，“你”和“她”就是中介者中的同事。

#### 图示

中介者模式结构图：

![中介者模式结构图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_mediator-pattern-structure.jpg)

#### 角色

从中介者模式结构图中可知，有以下4个角色：  

- （1）抽象中介者：定义了中介者
- （2）具体中介者：实现了抽象中介者的方法，它需要知道所有具体同事对象，并从具体同事对象接收消息，向具体同事对象发出命令。
- （3）抽象同事类：定义同事类
- （4）具体同事类：实现抽象同事类，每个具体同事对象只知道自己的行为，而不了解其他同事对象的情况，但它们都认识中介者。

#### 代码示例

这是一个悲伤的故事，住在村东边的你通过邮局给村西边的她表白，她说，她已经有男朋友了。

类图：

![中介者模式类图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_mediator-class-diagram.JPG)

抽象中介者角色：

```
public interface PostOffice {
    /**
     * 送信
     */
    void deliverLetters(String letters, String receiver);

    /**
     * 添加收信人
     */
    void addPeople(Villager villager);
}
```

具体中介者角色：

```
public class PostOfficeImpl implements PostOffice {
    /**
     * 收信人信息
     */
    private HashMap villagerMap = new HashMap<String, Villager>();

    @Override
    public void addPeople(Villager villager) {
        villagerMap.put(villager.getClass().getSimpleName(), villager);
    }

    @Override
    public void deliverLetters(String letters, String receiver) {
        System.out.println("=>收信：邮局收到要寄的信");
        Villager villager = (Villager) villagerMap.get(receiver);
        System.out.println("=>送信：拿出地址本查询收信人地址是：" + villager.getAddress() + "，送信");
        System.out.println("=>收信人看信：");
        villager.receiveLetter(letters);
    }
}
```

抽象同事类角色：

```
public abstract class Villager {
    protected PostOffice postOffice;
    protected String address;

    Villager(PostOffice postOffice, String address) {
        this.postOffice = postOffice;
        this.address = address;
    }

    public void receiveLetter(String letter) {
        System.out.println(letter);
    }

    public void sendLetter(String letter, String receiver) {
        postOffice.deliverLetters(letter, receiver);
    }

    public String getAddress() {
        return address;
    }
}

```

具体同事类角色：

```
// 她
public class She extends Villager {

    She(PostOffice postOffice, String address) {
        super(postOffice, address);
    }
}
// 你
public class You extends Villager {
    public You(PostOffice postOffice, String address) {
        super(postOffice, address);
    }
}
```

中介者模式测试类：
```
public class MediatorPatternTest {
    public static void main(String[] args) {
        PostOffice postOffice = new PostOfficeImpl();
        She she = new She(postOffice, "村西边");
        You you = new You(postOffice, "村东边");

        postOffice.addPeople(she);
        postOffice.addPeople(you);

        you.sendLetter("正月十五，元宵之夜，一见倾心", "She");
        she.sendLetter("对不起，我已经有男朋友了", "You");
    }
}
```

测试结果：

![中介者模式测试结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_mediator-pattern-result.jpg)

#### 使用场景

村子很大，人很多，关系很复杂：系统中存在很多对象，对象之间存在复杂的引用关系，产生的相互依赖关系结构混乱且难以理解，使得对象无法重用

人与人之间书信交流：对象间存在某种共性交互行为，用中介者封装这种行为

在这个很大的村子里面，每个人要给不同人的送信，这种关系成网状结构，错综复杂。

![应用中介者模式前](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_mediator-pattern-before-relationship.jpg)

加入邮局中介者之后，成星状结构，每个人只和邮局有关系。

![应用中介者模式后](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_mediator-pattern-after-relationship.jpg)

总结：系统中存在很多对象，对象间存在复杂的关系，在复杂的关系中存在共性交互行为，封装共性交互行为就是中介者。

中介者模式很容易在系统中应用，也很容易在系统中无用。当系统出现了“多对多”交互复杂的对象群是，不要急于使用中介者模式，而要先反思你的系统在设计上是不是合理。

实例有：联合国，聊天室等。

#### 中介者模式与迪米特法则

中介者模式是应用迪米特法则的典型。

迪米特法则：只与你最直接的朋友交流（Only talk to you immediate friends.）参考[设计模式六大原则](https://www.cnblogs.com/mingmingcome/p/10578208.html)

#### 优点

- 解耦：使同事类对象耦合性降低，可以独立变化和复用同事类
- 把对象如何协作进行了抽象，将中介作为一个独立的概念并将其封装在一个对象中，这样关注的对象就从对象各自本身的行为转移到它们之间的交互上来，也就是在一个更宏观的角度看待系统。

#### 缺点

- 在具体中介者类中包含了同事之间的交互细节，可能会导致具体中介者类非常复杂，不利于维护，后期可能有牵一发而动全身的危险。

#### 总结

中介者模式，用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地互相引用，从而使其耦合松散，而且可以独立地改变它们的交互。

#### 完

`2019年11月17日16:32:36`