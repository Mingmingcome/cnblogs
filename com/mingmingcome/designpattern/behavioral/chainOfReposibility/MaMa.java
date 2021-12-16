package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 14:08
 * @what 妈妈
 */
public class MaMa implements Support {
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
