package com.mingmingcome.designpattern.structural.composite.before;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Headquarters
 * @Description 公司总部
 * @Author luhaoming
 * @Date 2019/6/25 19:54
 */
public class Headquarters implements  Organization{
    private List<Department> departments = new ArrayList<>();

    @Override
    public void addOrg(Department department) {
        departments.add(department);
    }

    @Override
    public void removeOrg(Department department) {
        departments.remove(department);
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void showOrg() {
        System.out.println("-" + getName());
        for (Department department : departments) {
            System.out.println("--" +  department.getName());
        }
    }

    @Override
    public void displayDuty() {
        for(Department department : departments) {
            department.duty();
        }
    }
}
