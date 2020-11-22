package com.mingmingcome.designpattern.behavioral.strategy;

/**
 * @who luhaoming
 * @when 2020/9/6 18:44
 * @what 有才套路
 */
public class TalentedTaoLu implements TaoLu {

    @Override
    public void taoLu() {
        System.out.println("为她写诗");
        System.out.println("为她弹琴写词");
        System.out.println("为她摘星星月亮");
    }
}
