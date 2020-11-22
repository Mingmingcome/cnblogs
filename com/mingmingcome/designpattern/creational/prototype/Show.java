package com.mingmingcome.designpattern.creational.prototype;

/**
 * @ClassName Show
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/7/27 17:55
 */
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
