package com.mingmingcome.designpattern.creational.prototype;

/**
 * @ClassName PrototypeCapable
 * @Description 原型角色接口（继承Cloneable接口）
 * @Author luhaoming
 * @Date 2019/7/27 16:08
 */
public interface PrototypeCapable extends Cloneable {
    PrototypeCapable clone() throws CloneNotSupportedException;
}

