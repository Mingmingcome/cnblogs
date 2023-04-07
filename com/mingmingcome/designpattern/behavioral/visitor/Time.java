package com.mingmingcome.designpattern.behavioral.visitor;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Time 时间（抽象元素角色）
 */
public interface Time {
    void accept(Man man);
    String getName();
}
