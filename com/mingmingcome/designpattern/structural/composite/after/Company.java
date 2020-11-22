package com.mingmingcome.designpattern.structural.composite.after;

/**
 * @ClassName Company
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/7/16 20:05
 */
public interface Company {

    void addOrg(Company company);
    void removeOrg(Company company);

    void showOrg(int depth);

    default void print(int dept) {
        while(dept-- > 0) {
            System.out.print("-");
        }
    }
}
