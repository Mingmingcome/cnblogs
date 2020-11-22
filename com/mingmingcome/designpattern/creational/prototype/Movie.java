package com.mingmingcome.designpattern.creational.prototype;

/**
 * @ClassName Movie
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/7/27 17:48
 */
public class Movie implements PrototypeCapable {

    private String name = "钢铁侠";

    @Override
    public Movie clone() throws CloneNotSupportedException {
        System.out.println("拷贝Movie对象");
        return (Movie)super.clone();
    }

    @Override
    public String toString() {
        return "Movie{name='" + name + "'}";
    }
}
