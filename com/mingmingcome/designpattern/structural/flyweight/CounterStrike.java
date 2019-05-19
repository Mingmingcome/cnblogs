package com.mingmingcome.designpattern.structural.flyweight;

import java.util.Random;

/**
 * @ClassName CounterStrike
 * @Description 反恐精英客户端
 * @Author luhaoming
 * @Date 2019/5/19 21:45
 */
public class CounterStrike {
    private static String[] playerType = {"Terrorist", "CounterTerrorist"};

    private static String[] weapon = {"AK-47", "Maverick", "Gut Knife", "Desert Eagle"};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Player p = PlayerFactory.getPlayer(getPlayerType());
            p.assignWeapon(getWeapon());
            System.out.println(p.toString());
        }
    }

    private static String getPlayerType() {
        Random r = new Random();
        int i = r.nextInt(playerType.length);
        return playerType[i];
    }

    private static String getWeapon() {
        Random r = new Random();
        int i = r.nextInt(weapon.length);
        return weapon[i];
    }
}
