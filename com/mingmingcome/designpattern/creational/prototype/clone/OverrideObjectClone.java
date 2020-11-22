package com.mingmingcome.designpattern.creational.prototype.clone;

/**
 * @ClassName OverrideObjectClone
 * @Description 覆盖Object的clone方法
 * @Author luhaoming
 * @Date 2019/7/27 17:12
 */
public class OverrideObjectClone {
    private String s = "a";
    public static void main(String[] args) throws CloneNotSupportedException {
        OverrideObjectClone ooc = new OverrideObjectClone();
        OverrideObjectClone ooc1 = (OverrideObjectClone)ooc.clone();
        System.out.println(ooc);
        System.out.println(ooc1);
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
/*
Exception in thread "main" java.lang.CloneNotSupportedException: com.mingmingcome.designpattern.creational.prototype.clone.OverrideObjectClone
	at java.lang.Object.clone(Native Method)
	at com.mingmingcome.designpattern.creational.prototype.clone.OverrideObjectClone.clone(OverrideObjectClone.java:21)
	at com.mingmingcome.designpattern.creational.prototype.clone.OverrideObjectClone.main(OverrideObjectClone.java:15)

重写了Object的clone方法，方法里调用了Object的clone方法，运行抛异常。

Object类中注释已经说明：
The class {@code Object} does not itself implement the interface
 {@code Cloneable}, so calling the {@code clone} method on an object
 whose class is {@code Object} will result in throwing an
 exception at run time.

Object类自己并没有实现Cloneable接口，所以在一个Object类对象上调用clone方法将会导致在运行时抛出异常。
 */

class NonOverrideObjectClone {
    private String s = "a";
    public static void main(String[] args) throws CloneNotSupportedException {
        NonOverrideObjectClone ooc = new NonOverrideObjectClone();
        NonOverrideObjectClone ooc1 = (NonOverrideObjectClone)ooc.clone();
        System.out.println(ooc);
        System.out.println(ooc1);
    }
}

/**
 * Exception in thread "main" java.lang.CloneNotSupportedException: com.mingmingcome.designpattern.creational.prototype.clone.NonOverrideObjectClone
 * 	at java.lang.Object.clone(Native Method)
 * 	at com.mingmingcome.designpattern.creational.prototype.clone.NonOverrideObjectClone.main(OverrideObjectClone.java:46)
 * 	同样的原因
 */

class CloneableImpl implements Cloneable{
    private String s = "a";
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneableImpl c = new CloneableImpl();
        // 只需要实现Cloneable接口，即使不重写clone方法也不会报错
        CloneableImpl c1 = (CloneableImpl) c.clone();
        System.out.println(c.s);
        System.out.println(c.s == c1.s);
    }

}

