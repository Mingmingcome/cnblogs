package com.mingmingcome.designpattern.behavioral.visitor;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Past 过去
 */
public class Past implements Time {

    private String name = "过去";

    @Override
    public void accept(Man man) {
        man.visitPast(this);
    }

    public String getName() {
        return name;
    }
}
