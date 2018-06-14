<code>2018年1月9日17:06:47</code>

## 第二章 一切都是对象

>Java语言假设我们只进行面向对象的程序设计。

#### 2.1 用引用操纵对象

>每种编程语言都有自己的操纵内存元素的方式

操纵内存元素的方式：直接操纵元素、基于特殊语法的间接表示（如C和C++里的指针）操纵元素

在Java中，一切都被视为对象，操纵对象的标识符的是<i>引用</i>。

>可以将这一情形想像成用遥控器（引用）来操纵电视机（对象）。
>当有人想改变频道或者减小音量时，实际操纵的是遥控器（引用），再由遥控器来调控电视机对象（对象）。
>即使没有电视机，遥控器亦可独立存在。

创建一个String引用：
<pre><code>String s;</code></pre>

创建一个String引用并初始化：
<pre><code>String s = "Hello World!";</code></pre>

#### 2.2 必须由你创建所有对象

##### 2.2.1 存储在什么地方

存储数据的五个地方：
>1)<strong style="color: purple;">寄存器</strong>。这是<i style="color: blue;">最快</i>的存储区，因为它位于不同于其他存储区的地方——处理器内部。

>2)<strong style="color: purple;">堆栈</strong>。位于通用RAM（随机访问存储器）中，但通过堆栈指针可以从处理器那里获得直接支持。
><strong style="color: red;">堆栈指针若向下移动，则分配新的内存；若向上移动，则释放那些内存。</strong>这是一种快速有效的分配存储方法，<i>仅次于寄存器</i>。
>创建程序时，<strong style="color: #1e90ff;">Java程序必须知道存储在堆栈内所有项的确切生命周期</strong>，以便上下移动堆栈指针。

>3)<strong style="color: purple;">堆</strong>。一种通用的内存池也位于RAM区，<i>用于存放所有的Java对象</i>。
><strong style="color: #1e90ff;">堆不同于堆栈的好处是：编译器不需要知道存储的数据在堆里存活多久时间</strong>。
>当需要一个对象时，只需用new写一行简单的代码。
>用堆进行存储分配和清理可能要比用堆栈进行存储分配需要更多的时间。

>4)<strong style="color: purple;">常量存储</strong>。常量通常直接存放在程序代码内部，这样做是安全的，因为它们永远不会被改变。
>在嵌入式系统中，常量本身会和其他部分隔离开，所有这种情况下，可以选择将其存放在ROM（只读存储器）中。

>5)<strong style="color: purple;">非RAM存储</strong>。如果数据完全存活于程序之外，那么它就不受程序的任何控制，在程序没有运行是也可以存在。
>其中两个基本例子是<strong style="color: #1e90ff;"><i>流对象和持久化对象</i></strong>。
>在<strong><i>流对象</i></strong>中，对象转化成字节流，通常被发送给另一台机器。
>在“<strong><i>持久化对象</i></strong>”中，对象被存放于磁盘上，因此，即使程序终止，它们仍可以保持自己的状态。

堆栈是栈。（重要的事情说三遍，这是第二遍）

<strong>堆栈指针若向下移动，则分配新的内存；若向上移动，则释放那些内存。</strong>这句话之前一直都不理解，因为大学学习的数据结构中，栈底就是在底部，栈顶就是在顶部，所以堆栈指针向下移动的话，应该是释放内存的，所以这句话错了？当然不是！

linux中一个进程的虚拟内存分布:
![虚拟机内存分布](http://p2cc2nh3y.bkt.clouddn.com/chapter2-5-linux-memory.png)

![虚拟内存分布](http://p2cc2nh3y.bkt.clouddn.com/heap-stack.jpg)

图中0号地址在最下边，越往上内存地址越大。

以32位地址操作系统为例，一个进程可拥有的虚拟内存地址范围为0-2^32。分为两部分，一部分留给kernel使用(kernel virtual memory)，剩下的是进程本身使用， 即图中的process virtual memory。

普通Java 程序使用的就是process virtual memory.

上图中最顶端的一部分内存叫做user stack. 这就是题目问的 stack（栈）. 中间有个 runtime heap。就是题目中的heap（堆）. <strong>他们的名字和数据结构里的stack（栈） 和 heap（堆） 几乎没啥关系</strong>。

注意在上图中，stack（栈）是向下生长的; heap（堆）是向上生长的。

[引用这个答案](https://www.zhihu.com/question/29833675/answer/45811216)

所以呢，堆栈指针向下移动时分配内存，向上移动时释放内存。

##### 2.2.2 特例：基本类型

<strong>基本类型</strong>出现的原因：这种类型是小的、简单的变量，不需要用new在堆上分配空间，直接置于堆栈中，这样更高效。

>Java要确定每种基本类型所占存储空间的大小。

|基本类型|大小|最小值|最大值|包装器类型|
|--------|----|------|------|----------|
|boolean |-   |-     |-     |Boolean   |
|char    |16-bit|Unicode 0|Unicode 2^16-1|Character|
|byte    |8 bits|-128 |+127 |Byte|
|short   |16 bits|-2^15 |+2^15-1 |Short|
|int     |32 bits|-2^31 |+2^31-1 |Integer|
|long    |64 bits|-2^63 |+2^63-1 |Long|
|float   |32 bits|IEEE 754 |IEEE 754 |Float|
|double  |64 bits|IEEE 754 |IEEE 754 |Double|
|void    |-   |-     |-     |void   |

>所有数值类型都有<i>正负号</i>，所有不要去寻找无符号的数值类型。
>boolean类型所占存储空间的大小没有明确指定，仅定义为能够去字面值<i>true或false</i>。
>基本类型具有的包装类。

Java SE5引入了自动装箱（autoboxing）和拆箱（unboxing）。

自动装箱：（自动将基本类型转换成包装器类型）

    Character ch = 'x';

拆箱：（将包装器类型转换成基本类型）

    char c = ch;

<strong>高精度数字</strong>

用于高精度计算的类：<strong>BigInteger</strong>和<strong>BigDecimal</strong>。

>能作用于int或float的操作，也同样能作用于<strong>BigInteger</strong>或<strong>BigDecimal</strong>。只不过必须以方法调用方式取代运算符方式来实现。
><strong>BigInteger</strong>支持任何精度的整数。
><strong>BigDecimal</strong>支持任何精度的定点数。

##### 2.2.3 Java中的数组

>Java主要目标之一是<strong style="color: blue">安全性</strong>。
>Java确保数组会被初始化，而且不能在它的范围之外被访问。这种范围检查，是以每个数组上少量的内存开销及运行时的下标检查为代价。

数组初始化：存放对象的数组初始化为null，存在基本类型的数组初始化为0

#### 2.3 永远不需要销毁对象

##### 2.3.1 作用域

大多数过程型语言都有作用域的概念。作用域决定了在其内定义的变量名的可见性和生命周期。在C、C++和Java中，作用域由花括号的位置决定。

##### 2.3.2 对象的作用域

Java对象不具备和基本类型一样的生命周期。当用new创建一个Java对象时，它可以存活于作用域之外。

#### 2.4 创建新的数据类型：类

>大多数面向对象的程序设计语言习惯用关键子class来表示“我准备告诉你一种新类型的对象看起来像什么样子。

    class ATypeName { /* Class body goes here */ }

##### 2.4.1 字段和方法

在Java中你所做全部工作就是定义类，产生那些类的对象，以及发送消息给这些对象。

>一旦定义了一个类，就可以在类中设置两种类型的元素：<strong>字段</strong>（有时被称作<strong>数据成员</strong>）和<strong>方法</strong>（有时被称作称<strong>成员函数</strong>）。
>字段可以是任何类型的对象，可以通过其引用与其进行通信；也可以是基本类型中的一种。如果字段是对某个对象的引用，那么<i>必须初始化该引用</i>，以便使其与一个实际的对象相关联。
>每个对象都有用来存储其字段的空间；普通字段不能在对象间共享。

    class DataOnly {
        int i;
        double d;
        boolean b;
    }

如何引用一个对象的成员：
>在对象引用的名称之后紧接着一个句点，然后在接着是对象内部的成员名称:

    objectReference.member
 
如：
    
    DataOnly data = new DataOnly();
    data.i = 47;
    data.d = 1.1;
    data.b = false;

<strong>基本成员默认值</strong>

>若类的某个成员是基本数据类型，即使没有进行初始化，Java也会确保它获得一个默认值。

|基本类型|默认值|
|--------|------|
|boolean |false |
|char    |'\u0000'(null)|
|byte    |(byte)0|
|short   |(short)0|
|int     |0|
|long    |0L|
|float   |0.0f|
|double  |0.0d|

>这些初始值对你的程序来说，可能是<i>不正确的</i>，甚至是<i>不合法的</i>。所以<strong>最好明确地对变量进行初始化</strong>。

>然而上述确保初始化的方法并不适用于<stong>“局部”变量</stong>（即并非某个类的字段）。

在某个方法定义中有：

    int x;

如果忘记了<strong style="color: blue;">赋初值</strong>，Java会在编译是返回一个错误，告诉你此变量没有初始化。

#### 2.5 方法、参数和返回值

在Java中常用<strong style="color: blue;"><i>方法</i></strong>这个术语来表示“做某些事情的方式”。

>Java的方法决定了一个对象能够接收什么样的消息。方法的基本组成部分包括：名称、参数、返回值和方法体。

    ReturnType methodName( /* Argument list */ ) {
        /* Method body */
    }

ReturnType（返回类型）描述的是在调用方法之后才能够方法返回的值。Argument list（参数列表）给出了要传给方法的信息的类型和名称。

><i>方法名和参数列表</i>（它们合起来被称为<strong>“方法签名”</strong>）唯一地标识出某个方法。

>Java中的方法只能作为类的一部分来创建。方法只有通过对象才能被调用，且这个对象必须能执行这个方法调用。

调用格式：

    objectName.methodName(arg1, arg2, arg3);

这种调用方法的行为通常被称为<i>发送消息给对象</i>。

##### 2.5.1 参数列表

>方法的参数列表指定要传递给方法什么样的信息。

>在参数列表中必须指定每个所传递对象的类型和名字。像Java中任何传递对象的场合一样，这里传递的实际上也是引用，并且引用的类型必须正确。

note：除了基本类型外，通常，尽管传递的是对象，而实际传递的是对象的引用。

某个方法接受String为其参数：

    int storage(String s) {
        return s.length() * 2;
    }

方法告诉你，需要多少字节才能容纳一个特定的String对象。

return关键字的用法：
>首先，它代表“已经做完，离开此方法”。
>其次，如果此方法产生一个值，这个值要放在return语句之后。

你可以定义方法返回任意想要的类型，如果不想返回任何值，可以指示此方法返回void（空）。如：

    boolean flag() { return true; }
    double naturalLogBase() { return 2.718; }
    void nothing() { return; }
    void nothing2() {}

若返回类型是void，return关键字的作用只是用来退出方法。

note：

- 可以在任何地方return
- 返回类型不是void，无论在何处返回，编译器都会强制返回一个正确类型的返回值。

#### 2.6 构建一个Java程序

##### 2.6.1 名字可见性

>为了给一个类库生成不会与其他名字混淆的名字，Java设计者希望程序员反过来使用自己的Internet域名，因为这样可以保证它们肯定是独一无二的。

>在Java 1.0和Java 1.1中，扩展名com、edu、org、net等约定为大写形式。

    NET.mindview.utility.foibles

><strong style="color: blue;">现在整个包名都是小写了。</strong>

>这种机制意味这所有的文件都能够自动存活在它们自己的名字空间内，而且同一个文件内的每个类都有唯一的标识符。

##### 2.6.2 运用其他构件

>import来准确地告诉编译器你想要的类是什么。

    import java.util.ArrayList;

>这行代码告诉编译器，你想使用Java的ArrayList类。

##### 2.6.3 static关键字

static关键字满足这两方面的需要。
>一种情形是，<strong style="color: blue;">只想为某特定域分配单一存储空间，而不去考虑究竟要创建多少对象，甚至根本就不创建任何对象。</strong>

>另一种情形是，<strong style="color: blue;">希望某个方法不与包含它的那个类的任何对象实例关联在一起。</strong>也就是说，即使没有创建对象，也能够调用这个方法。

只须将static关键字放在定义之前，就可以将字段或方法设定为static。

    class StaticTest {
        static int i = 47;
    }

即使你创建了两个StaticTest对象，StaticTest.i也只有一份存储空间，这两个对象共享同一个**i**。

    StaticTest st1 = new StaticTest();
    StaticTest st2 = new StaticTest();

**st1.i**和**st2.i**指向同一存储空间，因此它们具有相同的值47。

>**<i style="color: blue;">使用类名</i>**是引用static变量的**首选方式**。

    StaticTest.i++;

同理，调用静态方法的**首选方式**：`ClassName.method()`。如：

    class Incrementable {
        static void increment() {
            StaticTest.i++;
        }
    }

调用方式一：

    Incrementable sf = new Incrementable();
    sf.increment();

调用方式二：

    Incrementable.increment();

>static方法可以创建或使用与其类型相同的被命名对象。

静态方法使用的都是静态某某。

只是static远远不止这么简单。挖坑。

#### 2.7 你的第一个Java程序

这次不是经典的Hello World了。

    import java.util.*;

    public class HelloDate {
        public static void main(String[] args) {
            System.out.println("Hello, it's: ");
            System.out.println(new Date());
        }
    }

>在每个程序文件的开头，必须声明import语句，以便引入在文件代码中需要用到的额外类。

**java.lang是默认导入每个Java文件中的。**

java.lang里没有Date()类，它位于util类库中，并且必须书写`import java.util.*`才能使用Date类。

**类的名字必须和文件名相同。**

如上代码，创建一个独立运行的程序，**这个类必须包含一个名为main()方法**。

看到这里，我有一个疑问:
>为什么现在我们开发的项目中都没有见到main()方法？

Google到Stack Overflow上的问题：
为什么我在这个java动态web项目中看不到任何main()方法？

[Why don't I see any main method in this java dynamic web project?
](https://stackoverflow.com/q/20900098)

答案：
这里还是有main()方法，只是不是由应用开发者写的，而是由容器开发者写的。

>There is still a main method, it's just not written by the developer of the application but by the developer of the container.

我是这样理解的：不是我这种渣渣写的，而是大神已经写好了。-_-

main()方法的参数是一个String对象的数组。Java编译器要求必须这么做，因为args要用来存储命令行参数。

##### 2.7.1 编译和运行

`javac`（编译）和`java`（运行）命令：

    javac HelloDate.java
    java HelloDate

#### 2.8 注释和嵌入式文档

单行注释：`// comment`

    // This is one-line comment

多行注释：（也可以是单行）`/* comment */`

    /* one-line comment */

    /** 
    * This is a comment
    * that continues
    * across lines
    */

##### 2.8.1 注释文档

>`javadoc`便是用于提取注释的工具，它是JDK安装的一部分。
>`javadoc`输出的是一个HTML文件，可以用Web浏览器查看。

##### 2.8.2 语法

>所有`javadoc`命令都只能在“`/**`”注释中出现，和通常一样，只是结束于“`*/`。
>使用`javadoc`的方式主要有两种：嵌入HTML，或使用“文档标签”。

共有三种类型的注释文档，分别对应于注释位置后面的三种元素：类、域和方法。

    /** A class comment */
    public class Documentation1 {
        /** A field comment */
        public int i;
        /** A method comment */
        public void f() {} 
    }

>`javadoc`只能为public和protected成员进行文档注释。

##### 2.8.3 嵌入式HTML
```
    /**
    * <pre>
    * System.out.println(new Date());
    * </pre>
    */
    public class Documentation2 {
        /** A field comment */
        public int i;
        /** A method comment */
        public void f() {} 
    }
```
##### 2.8.4 一些标签示例

略。

##### 2.8.5 文档示例

略。

#### 2.9 编码风格

>在“Java编程语言编码约定”中，代码风格是这样规定的：
>
