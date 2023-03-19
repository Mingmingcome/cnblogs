package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 11:36
 * @what 孩子（请求）
 */
public class You {
    private int age;

    private ObtainType obtainType;

    public ObtainType getObtainType() {
        return obtainType;
    }

    public void setObtainType(ObtainType obtainType) {
        this.obtainType = obtainType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
