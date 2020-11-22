package com.mingmingcome.designpattern.structural.composite.before;

/**
 * @ClassName Organization
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/6/25 19:49
 */
public interface Organization {
    void addOrg(Department department);
    void removeOrg(Department department);
    String getName();
    void showOrg();
    void displayDuty();
}
