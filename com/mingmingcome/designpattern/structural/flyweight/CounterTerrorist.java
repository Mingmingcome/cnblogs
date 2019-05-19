package com.mingmingcome.designpattern.structural.flyweight;

/**
 * @ClassName CounterTerrorist
 * @Description 反恐精英玩家
 * @Author luhaoming
 * @Date 2019/5/19 21:22
 */
public class CounterTerrorist implements Player{
    private final String task;

    private String weapon;

    public CounterTerrorist() {
        task = "放置炸弹";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "反恐精英{" +
                "task='" + task + '\'' +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
