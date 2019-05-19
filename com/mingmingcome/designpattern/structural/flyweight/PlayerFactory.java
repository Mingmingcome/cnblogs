package com.mingmingcome.designpattern.structural.flyweight;

import java.util.HashMap;

/**
 * @ClassName PlayerFactory
 * @Description 玩家工厂
 * @Author luhaoming
 * @Date 2019/5/19 21:24
 */
public class PlayerFactory {
    private static HashMap<String, Player> hm = new HashMap<>();

    public static Player getPlayer(String playerType) {
        Player p = null;

        if (hm.containsKey(playerType)) {
            p = hm.get(playerType);
        } else {
            switch (playerType) {
                case "Terrorist":
                    p = new Terrorist();
                    System.out.println("恐怖分子已创建");
                    break;
                case "CounterTerrorist":
                    p = new CounterTerrorist();
                    System.out.println("反恐精英已创建");
                    break;
                default:
                    System.out.println("无此玩家类型");
            }
            hm.put(playerType, p);
        }
        return p;
    }
}
