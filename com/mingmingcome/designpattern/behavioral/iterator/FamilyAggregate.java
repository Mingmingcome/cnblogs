package com.mingmingcome.designpattern.behavioral.iterator;

/**
 * @who luhaoming
 * @when 2021/12/1 20:24
 * @what 家集合
 */
public interface FamilyAggregate {
    NameIterator createIterator();
    boolean addFamily(int generation, String name);
}
