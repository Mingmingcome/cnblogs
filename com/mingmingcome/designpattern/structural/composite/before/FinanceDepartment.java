package com.mingmingcome.designpattern.structural.composite.before;

/**
 * @ClassName FinanceDepartment
 * @Description 财务部
 * @Author luhaoming
 * @Date 2019/6/25 20:33
 */
public class FinanceDepartment implements Department{

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void duty() {
        System.out.println("财务部负责公司财务收支管理");
    }
}
