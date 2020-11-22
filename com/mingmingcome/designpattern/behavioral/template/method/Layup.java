package com.mingmingcome.designpattern.behavioral.template.method;

/**
 * @who luhaoming
 * @when 2020/9/13 15:32
 * @what 上篮
 */
public abstract class Layup {

    public final boolean layupTemplate() {
        boolean flag = catchTheBall();
        flag = dribble(flag);
        flag = stride(flag);
        flag = throwOrLayTheBall(flag);
        return score(flag);
    }

    public final boolean catchTheBall() {
        System.out.println("接球");
        return true;
    }

    protected abstract boolean dribble(boolean flag);
    protected abstract boolean stride(boolean flag);
    protected abstract boolean throwOrLayTheBall(boolean flag);

    public final boolean score(boolean flag) {
        if (flag) {
            System.out.println("上篮成功，得分");
            return true;
        } else {
            System.out.println("上篮失败");
            return false;
        }

    }
}
