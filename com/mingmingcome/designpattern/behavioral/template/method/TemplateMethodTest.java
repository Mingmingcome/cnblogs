package com.mingmingcome.designpattern.behavioral.template.method;

import java.util.Random;

/**
 * @who luhaoming
 * @when 2020/9/13 16:09
 * @what 模板方法模式测试类
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
        Layup layup;

        // 科比上篮
        layup = new KobeLayup();
        layup.layupTemplate();

        System.out.println("--------------------------------------------");
        // 菜鸟上篮
        layup = new RookieLayup();
        boolean flag;
        int i = 1;
        do {
            System.out.println("菜鸟第" + i++ + "次上篮");
            flag = layup.layupTemplate();
        } while (!flag);

    }
}
