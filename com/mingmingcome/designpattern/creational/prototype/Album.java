package com.mingmingcome.designpattern.creational.prototype;

/**
 * @ClassName Album
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/7/27 17:53
 */
public class Album implements PrototypeCapable {

    private String name = "无法长大";

    @Override
    public Album clone() throws CloneNotSupportedException {
        System.out.println("拷贝Album对象");
        return (Album)super.clone();
    }

    @Override
    public String toString() {
        return "Album{name='" + name + "'}";
    }
}