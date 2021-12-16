package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 14:04
 * @what 爸爸
 */
public class BaBa implements Support{
    private int age = 30;
    private Support successor;
    @Override
    public void setSuccessor(Support successor) {
        this.successor = successor;
    }

    @Override
    public void handle(You you) {

    }

    public int getAge() {
        return age;
    }

    public void incrAge() {
        this.age++;
    }
}
