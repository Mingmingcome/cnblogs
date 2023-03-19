package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 14:08
 * @what 妈妈
 */
public class MaMa implements Support {
    private String name = "妈妈";
    private Support successor;
    @Override
    public void setSuccessor(Support successor) {
        this.successor = successor;
    }

    @Override
    public void handle(You you) {
        if (you.getObtainType() == ObtainType.NAME) {
            System.out.println(name + ":怀孕了，起个好听的名字");
            successor.handle(you);
        } else if (you.getObtainType() == ObtainType.BORN) {
            System.out.println(name + ":十月怀胎，你出生了");
            successor.handle(you);
        } else if (you.getObtainType() == ObtainType.EDUCATION) {
            System.out.println(name + ":十六年努力工作，提供给你最好的教育");
            successor.handle(you);
        } else if (you.getObtainType() == ObtainType.WORK) {
            System.out.println(name + ":给你鼓励");
            successor.handle(you);
        } else if (you.getObtainType() == ObtainType.MARRIAGE) {
            System.out.println(name + ":倾尽所有买房买车");
            successor.handle(you);
        }
    }
}
