package com.mingmingcome.designpattern.behavioral.visitor;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Optimistic 乐观主义者
 */
public class Optimist implements Man {
    @Override
    public void visitPast(Past past) {
        System.out.println("不念" + past.getName());
    }

    @Override
    public void visitPresent(Present present) {
        System.out.println("享受" + present.getName());
    }

    @Override
    public void visitFuture(Future future) {
        System.out.println("不畏" + future.getName());
    }
}
