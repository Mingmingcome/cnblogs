package com.mingmingcome.designpattern.structural.composite.after;

/**
 * @ClassName FinanceDepartment
 * @Description 财务部
 * @Author luhaoming
 * @Date 2019/7/16 20:22
 */
public class FinanceDepartment implements Company {
    private String name = "";
    FinanceDepartment(String name) {
        this.name = name;
    }

    @Override
    public void addOrg(Company company) {

    }

    @Override
    public void removeOrg(Company company) {

    }

    @Override
    public void showOrg(int dept) {
        print(dept);
        System.out.println(this.name);
    }
}
