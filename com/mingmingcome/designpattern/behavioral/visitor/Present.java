package com.mingmingcome.designpattern.behavioral.visitor;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Present 现在
 */
public class Present implements Time {

    private String name = "现在";

    @Override
    public void accept(Man man) {
        man.visitPresent(this);
    }
    public String getName() {
        return name;
    }
}
