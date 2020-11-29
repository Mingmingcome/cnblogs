package com.mingmingcome.designpattern.behavioral.memento;

/**
 * @who luhaoming
 * @when 2020/11/29 21:03
 * @what 备忘录测试类
 */
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
