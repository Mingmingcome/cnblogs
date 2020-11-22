package com.mingmingcome.designpattern.structural.composite.before;

/**
 * @ClassName Before
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/6/25 20:18
 */
public class Before{
    public static void main(String[] args) throws CloneNotSupportedException {
        Organization hq = new Headquarters();

        Department fnc = new FinanceDepartment();

        Department hr = new HumanResourceDepartment();

        hq.addOrg(fnc);
        hq.addOrg(hr);

        System.out.println("====组织结构====");
        hq.showOrg();
        System.out.println("====部门职责====");
        hq.displayDuty();
    }

}
