package com.mingmingcome.designpattern.behavioral.strategy;

/**
 * @who luhaoming
 * @when 2020/9/6 18:41
 * @what 富豪套路
 */
public class RichTaoLu implements TaoLu {

    @Override
    public void taoLu() {
        System.out.println("为她买金");
        System.out.println("为她买银");
        System.out.println("为她买包包");
    }
}
