package com.mingmingcome.designpattern.behavioral.template.method;

/**
 * @who luhaoming
 * @when 2020/9/13 15:43
 * @what 科比上篮
 */
public class KobeLayup extends Layup {

    @Override
    public boolean dribble(boolean flag) {
        if (!flag) return false;
        System.out.println("科比运球，来个crossover过人");
        return true;
    }

    @Override
    public boolean stride(boolean flag) {
        if (!flag) return false;
        System.out.println("科比三分线处上篮起步，一步，二步");
        return true;
    }

    @Override
    public boolean throwOrLayTheBall(boolean flag) {
        if (!flag) return false;
        System.out.println("科比起飞，手轻轻一挑，球空心入框");
        return true;
    }
}
