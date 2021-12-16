package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 14:09
 * @what 家人是你一生的支持
 */
public interface Support {
    void setSuccessor(Support successor);
    void handle(You you);
}
