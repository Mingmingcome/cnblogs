package com.mingmingcome.designpattern.behavioral.mediator;

/**
 * @ClassName Villager
 * @Description 抽象同事类
 * @Author luhaoming
 * @Date 2019/11/16 1:24
 */
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
