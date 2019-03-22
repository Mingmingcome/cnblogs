package com.mingmingcome.designpattern.principle.lsp;

public class Mouse implements Animal{
    private String name = "老鼠";
    @Override
    public String getName() {
        return this.name;
    }
}
