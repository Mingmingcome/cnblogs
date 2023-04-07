package com.mingmingcome.designpattern.behavioral.visitor;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Pessimistic 悲观主义者
 */
public class Pessimist implements Man {
    @Override
    public void visitPast(Past past) {
        System.out.println("悔恨" + past.getName());
    }

    @Override
    public void visitPresent(Present present) {
        System.out.println("焦虑" + present.getName());
    }

    @Override
    public void visitFuture(Future future) {
        System.out.println("迷茫" + future.getName());
    }
}
