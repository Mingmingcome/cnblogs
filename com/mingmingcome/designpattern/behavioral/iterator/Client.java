package com.mingmingcome.designpattern.behavioral.iterator;

/**
 * @who luhaoming
 * @when 2021/12/2 12:41
 * @what 客户端
 */
public class Client {
    public static void main(String[] args) {
        FamilyAggregate family = new FamilyAggregateImpl(3);

        family.addFamily(1, "晓月");
        family.addFamily(2, "爸爸");
        family.addFamily(2, "妈妈");
        family.addFamily(2, "叔叔");
        family.addFamily(2, "婶婶");
        family.addFamily(3, "爷爷");
        family.addFamily(3, "奶奶 ");

        NameIterator iterator = family.createIterator();
        while (iterator.hastNext()) {
            String name = iterator.next();
            System.out.println(name);
        }

    }
}
