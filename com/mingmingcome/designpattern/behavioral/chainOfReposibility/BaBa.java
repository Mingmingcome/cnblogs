package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 14:04
 * @what 爸爸
 */
public class BaBa implements Support{
    private String name = "爸爸";
    private Support successor;
    @Override
    public void setSuccessor(Support successor) {
        this.successor = successor;
    }

    @Override
    public void handle(You you) {
        if (you.getObtainType() == ObtainType.NAME) {
            System.out.println(name + ":让岳父起个好听的名字");
            successor.handle(you);
        } else if (you.getObtainType() == ObtainType.BORN) {
            System.out.println(name + ":高兴");
            successor.handle(you);
        } else if (you.getObtainType() == ObtainType.EDUCATION) {
            System.out.println(name + ":十六年和妈妈一起努力工作，提供给你最好的教育");
            successor.handle(you);
        } else if (you.getObtainType() == ObtainType.WORK) {
            System.out.println(name + ":给你指导");
            successor.handle(you);
        } else if (you.getObtainType() == ObtainType.MARRIAGE) {
            System.out.println(name + ":和妈妈一起倾尽所有买房买车");
            successor.handle(you);
        }
    }
}
