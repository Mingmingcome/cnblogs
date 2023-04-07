package com.mingmingcome.designpattern.behavioral.visitor;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Man 人类(抽象访问者角色）
 */
public interface Man {
    void visitPast(Past past);
    void visitPresent(Present present);
    void visitFuture(Future future);
}
