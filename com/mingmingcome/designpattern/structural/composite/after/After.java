package com.mingmingcome.designpattern.structural.composite.after;

/**
 * @ClassName After
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/7/16 19:52
 */
public class After {
    public static void main(String[] args) {
        Company headquarters = new ConcreteComp("公司总部");
        headquarters.addOrg(new FinanceDepartment("财务部"));
        headquarters.addOrg(new HumanResourceDepartment("人力资源部"));

        Company gd = new ConcreteComp("广东分公司");
        gd.addOrg(new FinanceDepartment("财务部"));
        gd.addOrg(new HumanResourceDepartment("人力资源部"));

        Company sz = new ConcreteComp("深圳分公司");
        sz.addOrg(new FinanceDepartment("财务部"));
        sz.addOrg(new HumanResourceDepartment("人力资源部"));

        gd.addOrg(sz);

        Company sh = new ConcreteComp("上海分公司");
        sh.addOrg(new FinanceDepartment("财务部"));
        sh.addOrg(new HumanResourceDepartment("人力资源部"));

        headquarters.addOrg(gd);
        headquarters.addOrg(sh);

        headquarters.showOrg(1);

        System.out.println();
        System.out.println("===========================");
        Company test = new FinanceDepartment("测试财务部");
        test.addOrg(new HumanResourceDepartment("人力资源部"));
        test.addOrg(sh);

        test.showOrg(1);
    }
}
