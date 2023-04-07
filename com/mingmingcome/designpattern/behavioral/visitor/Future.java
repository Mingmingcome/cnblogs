package com.mingmingcome.designpattern.behavioral.visitor;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Future 未来
 */
public class Future implements Time {

    private String name = "未来";

    @Override
    public void accept(Man man) {
        man.visitFuture(this);
    }

    public String getName() {
        return name;
    }
}
