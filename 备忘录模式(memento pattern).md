`2020年11月29日15:25:07`

## 备忘录模式

#### 引子

>曾经有一份真挚的爱情摆在我的面前，但是我没有珍惜，等我失去后才后悔莫及，尘世间最痛苦的事情莫过于此。

>如果上天能够给我一个再来一次的机会，我会对那个女孩说三个字：我爱你。

>如果非要在这份爱上加一个期限，我希望是一万年！

>——至尊宝

#### 定义

>Without violating encapsulation, capture and externalize an object's internal
state so that the object can be restored to this state later. ——《Design Patterns: Elements of Reusable Object-Oriented Software》

备忘录模式（memento pattern），在不破坏封装型的前提下，获取并保存一个对象的内部状态，以便以后对象可以恢复到这个状态。——《设计模式：可复用面向对象软件的基础》

#### 图示

备忘录模式结构图：

![备忘录模式结构图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_201129103703memento-pattern-structure-diagram.png)

结构图显示了备忘录模式的对象角色有三，一是发起人(Originator)， 二是备忘录(Memento)，三是管理者(Caretaker)。

备忘录模式流程图：

![备忘录模式流程图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_201129120602memento-pattern-sequence-diagram.png)

流程图展示了备忘录模式的工作流程：

1、管理者(Caretaker)调用发起人(Originator)createMemento创建备忘录保存状态

2、管理者调用发起人的setMemento从备忘录获取备份的状态恢复

#### 角色

发起人(Originator)：从结构图中的两个方法createMemento和setMemento可以看出，发起人负责的是创建一个备忘录Memento，以及在需要的时候使用Memento恢复自己的内部状态。发起人可以根据需要决定保存和恢复哪些内部状态。

备忘录(Memento)：负责存在发起人(Originator)对象的内部状态，并可防止Originator以外的其他对象访问备忘录(Memento)。备忘录有两个接口，Caretaker只能看到备忘录的窄接口，它只能将备忘录传递给其他对象；Originator能够看到一个宽接口，允许他访问返回到先前状态所需的所有数据。怎么做？Memento作为Originator的内部类就是可以实现了。

管理者(Caretaker)：负责保存好备忘录，不能对备忘录的内容进行操作或检查。

#### 代码示例

故事背景：

小时候，总想着长大，但是谁知道长大后一点有不好玩，朝九晚九。

我想玩电视剧回到年轻的剧情，让我好好感受在春天放牛，夏天放鸭，秋天抓鱼，冬天等小猪出生的日子。

有天我捡到一个相框，只要我把小时候的照片放到相框里，我就可以回到小时候。

发起人(Originator)和备忘录(Memento)：
``` java
// 我
public class I {
    private int age;
    private String doingWhat;

    public Photo createPhoto() {
        return new Photo(this.age, this.doingWhat);
    }

    public void backToThePast(Photo photo) {
        this.age =  photo.getAge();
        this.doingWhat = photo.getDoingSomething();
        System.out.println("时光倒流，我现在是" + age + "岁，可以：" + doingWhat);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDoingWhat(String doingWhat) {
        this.doingWhat = doingWhat;
    }

    public void print() {
        System.out.println("我现在是" + age + "岁，可以：" + doingWhat);
    }

    // 照片(备忘录)
    public class Photo {
        private int age;
        private String doingWhat;

        public Photo(int age, String doingWhat) {
            this.age = age;
            this.doingWhat = doingWhat;
        }

        private int getAge() {
            return age;
        }

        private String getDoingSomething() {
            return doingWhat;
        }

    }
}
```

作为备忘录角色的照片（photo）是我（I）的内部类，对我（I）来说是拥有宽接口包含getAge、getDoingSomething等方法，当然你也可以加其他方法，而这些方法对于其他对象是不可见的。

管理者(Caretaker)：
``` java
// 时光倒流相框
public class Frame {
    private List<I.Photo> photoList = new ArrayList<>();

    public I.Photo getPhoto(int i) {
        return photoList.get(i);
    }

    public void add(I.Photo photo) {
        photoList.add(photo);
    }

}
```

测试类：
``` java
public class MementoPatternTest {
    public static void main(String[] args) {
        I i = new I();

        Frame frame = new Frame();

        i.setAge(12);
        i.setDoingWhat("春天放牛，夏天放鸭，秋天抓鱼，冬天等小猪出生");
        i.print();
        frame.add(i.createPhoto());

        i.setAge(18);
        i.setDoingWhat("念书");
        frame.add(i.createPhoto());
        i.print();

        i.setAge(25);
        i.setDoingWhat("工作");
        i.print();

        i.backToThePast(frame.getPhoto(0));
    }
}
```

测试结果图：

![备忘录模式测试结果图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_201129133309memento-pattern-test-result.jpg)

#### 使用场景

如果你需要创建对象状态的快照来恢复对象之前的状态时，你可以使用备忘录模式。

如果直接访问对象的状态会破坏封装，可以使用备忘录模式。

#### 优点

- 你可以在不破坏对象封装情况的前提下创建对象状态快照。

#### 缺点

- 如果客户端过于频繁地创建备忘录， 程序将消耗大量内存。
- 管理者必须完整跟踪原发器的生命周期， 这样才能销毁弃用的备忘录。一般来说是这样的，但是Java有垃圾回收器，管理者不存在，备忘录也会被自动回收。

#### 总结

备忘录模式，在不破坏封装型的前提下，获取并保存一个对象的内部状态，以便以后对象可以恢复到这个状态。该模式有三个角色：发起人(Originator)、备忘录(Memento)、管理者(caretaker)。发起人创建备忘录和利用备忘录恢复状态，备忘录保存发起人状态，管理者保存备忘录。

`2020年11月29日21:48:47`