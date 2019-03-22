package com.mingmingcome.designpattern.principle.lsp;

public class Dog implements Animal{
    private String name = "狗";
    @Override
    public String getName() {
        return this.name;
    }
}
