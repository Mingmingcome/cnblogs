package com.mingmingcome.designpattern.behavioral.template.method;

import java.util.Random;

/**
 * @who luhaoming
 * @when 2020/9/13 15:46
 * @what 菜鸟上篮
 */
public class RookieLayup extends Layup {

     private Random random = new Random();

    @Override
    public boolean dribble(boolean flag) {
        if (!flag) return false;
        if (random.nextInt(100) > 60) {
            System.out.println("菜鸟运球");
            return true;
        } else {
            System.out.println("菜鸟运球，手滑了，球丢了");
            return false;
        }
    }

    @Override
    public boolean stride(boolean flag) {
        if (!flag) return false;
        if (random.nextInt(100) > 60) {
            System.out.println("菜鸟上篮起步，一步，两步");
            return true;
        } else {
            System.out.println("菜鸟上篮起步，一步，两步，三步，走步了");
            return false;
        }
    }

    @Override
    public boolean throwOrLayTheBall(boolean flag) {
        if (!flag) return false;
        if (random.nextInt(100) > 60) {
            System.out.println("菜鸟瞄准篮筐，手挑一个，球进了");
            return true;
        } else {
            System.out.println("菜鸟瞄准篮筐，手挑一个，太用力了，哐，打铁了");
            return false;
        }
    }
}
