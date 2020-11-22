package com.mingmingcome.designpattern.structural.composite.after;

/**
 * @ClassName HumanResourceDepartment
 * @Description 人力资源部
 * @Author luhaoming
 * @Date 2019/7/16 20:30
 */
public class HumanResourceDepartment implements Company {
    private String name = "";
    HumanResourceDepartment(String name) {
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
