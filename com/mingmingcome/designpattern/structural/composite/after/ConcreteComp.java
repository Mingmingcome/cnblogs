package com.mingmingcome.designpattern.structural.composite.after;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Branch
 * @Description 分公司
 * @Author luhaoming
 * @Date 2019/7/16 20:31
 */
public class ConcreteComp implements Company {
    private String name = "";
    private List<Company> companies = new ArrayList<>();
    ConcreteComp(String name) {
        this.name = name;
    }

    @Override
    public void addOrg(Company company) {
        companies.add(company);
    }

    @Override
    public void removeOrg(Company company) {
        companies.remove(company);
    }

    @Override
    public void showOrg(int dept) {
        print(dept);
        System.out.println(this.name);
        for (Company c : companies) {
            c.showOrg(dept + 2);
        }
    }

}
