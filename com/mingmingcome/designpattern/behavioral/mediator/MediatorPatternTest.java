package com.mingmingcome.designpattern.behavioral.mediator;

/**
 * @ClassName MediatorPatternTest
 * @Description 中介者模式测试
 * @Author luhaoming
 * @Date 2019/11/17 12:16
 */
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
