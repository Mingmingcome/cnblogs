package com.mingmingcome.designpattern.structural.flyweight;

/**
 * @ClassName Terrorist
 * @Description 恐怖分子玩家
 * @Author luhaoming
 * @Date 2019/5/19 21:16
 */
public class Terrorist implements Player{
    private final String task;

    private String weapon;

    public Terrorist() {
        task = "放置炸弹";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "恐怖分子{" +
                "task='" + task + '\'' +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
