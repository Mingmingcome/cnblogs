package com.mingmingcome.designpattern.structural.composite.before;

/**
 * @ClassName HumanResourceDepartment
 * @Description 人力资源部
 * @Author luhaoming
 * @Date 2019/6/25 20:01
 */

public class HumanResourceDepartment implements Department{

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void duty() {
        System.out.println("人力资源部负责员工招聘培训管理");
    }
}