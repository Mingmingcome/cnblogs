package com.mingmingcome.designpattern.structural.flyweight;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName CounterStrike
 * @Description 反恐精英客户端 参考：https://www.geeksforgeeks.org/flyweight-design-pattern/
 * @Author luhaoming
 * @Date 2019/5/19 21:45
 */
public class CounterStrike {
    private static String[] playerType = {"Terrorist", "CounterTerrorist"};

    private static String[] weapon = {"AK-47", "Maverick", "Gut Knife", "Desert Eagle"};

    private static List<Player> terrorist = new ArrayList<>();
    private static List<Player> counterTerrorist = new ArrayList<>();

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        for (int i = 0; i < 10; i++) {
            String playerType = getPlayerType();
            Player p = PlayerFactory.getPlayer(playerType);
            p.assignWeapon(getWeapon());
            System.out.println(p);
            if ("Terrorist".equals(playerType)) {
                terrorist.add(p);
            } else {
                counterTerrorist.add(p);
            }
        }
        test(terrorist, counterTerrorist);
    }

    private static void test(List<Player> terrorist, List<Player> counterTerrorist) throws IllegalAccessException, NoSuchFieldException {
        // 测试成果标志
        boolean flag = true;
        for (int i = 0; i < terrorist.size(); i++) {
            for (int j = 0; j < terrorist.size(); j++) {
                // 所有恐怖分子的内部状态都是一致的，即task一致
                if (terrorist.get(i).getTask().equals(terrorist.get(j).getTask())) {
                    continue;
                } else {
                    flag = false;
                }
            }
        }
        for (int i = 0; i < counterTerrorist.size(); i++) {
            for (int j = 0; j < counterTerrorist.size(); j++) {
                // 所有反恐精英的内部状态都是一致的，即task一致
                if (counterTerrorist.get(i).getTask().equals(counterTerrorist.get(j).getTask())) {
                    continue;
                } else {
                    flag = false;
                }
            }
        }
        if (flag) {
            System.out.println("享元模式验证成功");
        } else {
            System.out.println("享元模式验证失败");
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
