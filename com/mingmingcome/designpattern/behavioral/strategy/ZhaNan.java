package com.mingmingcome.designpattern.behavioral.strategy;

/**
 * @who luhaoming
 * @when 2020/9/6 19:16
 * @what 策略模式测试类：渣男
 */
public class ZhaNan {
    public static void main(String[] args) {
        KuiHuaBaoDian kuiHuaBaoDian;

        String girl1 = "拜金女";
        // 选择策略
        TaoLu taoLu1 = new RichTaoLu();
        // 创建上下文
        kuiHuaBaoDian = new KuiHuaBaoDian(taoLu1);
        kuiHuaBaoDian.toGetTheGirlIntoBed(girl1);

        String girl2 = "爱才女";
        // 选择策略
        TaoLu taoLu2 = new TalentedTaoLu();
        // 创建上下文
        kuiHuaBaoDian = new KuiHuaBaoDian(taoLu2);
        kuiHuaBaoDian.toGetTheGirlIntoBed(girl2);
    }
}
