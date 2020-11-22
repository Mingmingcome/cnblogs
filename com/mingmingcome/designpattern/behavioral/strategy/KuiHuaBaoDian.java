package com.mingmingcome.designpattern.behavioral.strategy;

/**
 * @who luhaoming
 * @when 2020/9/6 19:04
 * @what 葵花宝典
 */
public class KuiHuaBaoDian {
    // 持有套路（Strategy）的引用
    private TaoLu taoLu;

    public KuiHuaBaoDian(TaoLu taoLu) {
        this.taoLu = taoLu;
    }

    public void toGetTheGirlIntoBed(String girl) {
        taoLu.taoLu();
        System.out.println("你得到了" + girl + "的心");
    }
}
