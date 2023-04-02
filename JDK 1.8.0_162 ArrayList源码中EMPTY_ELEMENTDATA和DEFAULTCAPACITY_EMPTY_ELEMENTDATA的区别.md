`2018年7月22日09:54:17`

## JDK 1.8.0_162 ArrayList源码中EMPTY_ELEMENTDATA和DEFAULTCAPACITY_EMPTY_ELEMENTDATA的区别

写在前面的话：

关于阅读源码：刚开始学习的时候，觉得阅读源码是多么遥远的事情，但是不知不觉已经毕业一年了，自己的进步不多。华罗庚说，“自学，不怕起点低，就怕不到底”。阅读源码应该是比较“底”了吧，哈哈。阅读源码，在面试官问你这个问题：“你读过Java源码吗”的时候，你可以拍着胸口回答他：“读过！！！”。Last but not least，就是可以装逼：我已经读过Java源码了。（虽然不知道自己收获了多少）

言归正传，《Effective Java》第二版<i>第47条：了解和使用类库</i>中有这么一句话：<b>每个程序员都应该熟悉java.lang、java.util，某种程度上还有java.io中的内容。</b>然后我就从java.util开始读了。

本文只是讨论JDK 1.8.0_162中EMPTY_ELEMENTDATA和DEFAULTCAPACITY_EMPTY_ELEMENTDATA的区别，关于源码详细解读请Google。

在ArrayList中有关EMPTY_ELEMENTDATA（下文用EE代替）和DEFAULTCAPACITY_EMPTY_ELEMENTDATA（下文用DEE代替）的声明定义如下：
```java
/**
 * Shared empty array instance used for empty instances.
 * 用于ArrayList空实例的共享空数组实例
 */
private static final Object[] EMPTY_ELEMENTDATA = {};

/**
 * Shared empty array instance used for default sized empty instances. We
 * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
 * first element is added.
 * 用于默认大小空实例的共享空数组实例。我们将this（DEFAULTCAPACITY_EMPTY_ELEMENTDATA）
 * 和EMPTY_ELEMENTDATA区别开来，以便在添加第一个元素时知道要膨胀多少。
 */
private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
```

这两个类常量EE和DEE都是表示空数组，只是名字不一样而已。

三个构造函数：
```java
/**
 * 有参
 */
public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
    	// 这里
        this.elementData = EMPTY_ELEMENTDATA;
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    }
}

/**
 * 无参
 */
public ArrayList() {
	// 这里
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

/**
 * 参数为集合
 */
public ArrayList(Collection<? extends E> c) {
    elementData = c.toArray();
    if ((size = elementData.length) != 0) {
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    } else {
        // replace with empty array. 这里
        this.elementData = EMPTY_ELEMENTDATA;
    }
}
```

其中无参构造器创建的实例al的elementData是DEE，有参构造函数创建的空实例al1和al2的elementData是EE。即：
```java
// elementData = DEE
ArrayList<String> al = new ArrayList<String>();

// elementData = EE
ArrayList<String> al1 = new ArrayList<String>(0);
ArrarList<String> al2 = new ArrayList<String>(al1)
```

接下来看看add(E e)方法：
```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}

private static int calculateCapacity(Object[] elementData, int minCapacity) {
	// 当第一次调用add(E e)方法的时候，判读是不是无参构造函数创建的对象，如果是，
	// 将DEFAULT_CAPACITY即10作为ArrayList的容量，此时minCapacity = 1
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        return Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    return minCapacity;
}

private void ensureCapacityInternal(int minCapacity) {
    ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
}

private void ensureExplicitCapacity(int minCapacity) {
    modCount++;

    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}
```

其他add方法如：add(int index, E element)、addAll(Collection<? extends E> c)、addAll(int index, Collection<? extends E> c)中都有ensureCapacityInternal(int minCapacity)方法，确保无参构成函数创建的实例al在添加第一个元素时，<i>最小的容量</i>是默认大小10。那有参构造函数创建的空实例al1、al2在通过add(E e)添加元素的时候是怎么样的呢？al1、al2容量增长是这样子的：0->1->2->3->4->6->9->13...，这样的增长是很慢的。具体扩容方式：
```java
private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    // 新容量为旧容量的1.5倍
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```

问题：两个类常量都是表示空数组，为什么要用两个呢？在Java7中只有一个类常量表示空数组，就是EE。Java8中添加了DEE代替了EE。

在Java7中ArrayList的构造函数：
```java
public ArrayList(int initialCapacity) {
   super();
   if (initialCapacity < 0)
       throw new IllegalArgumentException("Illegal Capacity: "+
                                          initialCapacity);
   this.elementData = new Object[initialCapacity];
}

public ArrayList() {
   super();
   this.elementData = EMPTY_ELEMENTDATA;
}

public ArrayList(Collection<? extends E> c) {
   elementData = c.toArray();
   size = elementData.length;
   // c.toArray might (incorrectly) not return Object[] (see 6260652)
   if (elementData.getClass() != Object[].class)
       elementData = Arrays.copyOf(elementData, size, Object[].class);
}
```

完全就是DEE代替了EE。那EE干什么去了，看一下构造函数中EE安排在哪里了？都是在判断容量为空的情况下，赋值给elementData。Java7中如果容量是0的话，会创建一个空数组，赋值给elementData:`this.elementData = new Object[initialCapacity];`、`elementData = Arrays.copyOf(elementData, size, Object[].class);`。如果一个应用中有很多这样ArrayList空实例的话，就会有很多的空数组，无疑EE是为了优化性能，所有ArrayList空实例都指向同一个空数组。问题解决。

题外话：《Effective Java》第二版<i>第43条：返回零长度的数组或集合，而不是null</i>。难道因为这个建议让ArrayList空实例增加了，所以类库的编写者作出了这个优化，哈哈。

总结之EMPTY_ELEMENTDATA和DEFAULTCAPACITY_EMPTY_ELEMENTDATA的区别：<b>EMPTY_ELEMENTDATA是为了优化创建ArrayList空实例时产生不必要的空数组，使得所有ArrayList空实例都指向同一个空数组。DEFAULTCAPACITY_EMPTY_ELEMENTDATA是为了确保无参构成函数创建的实例在添加第一个元素时，<i>最小的容量</i>是默认大小10。</b>

`2018年7月22日16:48:05`
