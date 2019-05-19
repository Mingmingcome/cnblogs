package com.mingmingcome.designpattern.structural.flyweight;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * @ClassName StringDemo
 * @Description String与享元模式
 * @Author luhaoming
 * @Date 2019/5/14 22:56
 */
public class StringDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String a = "mingmingcome";
        String b = "mingmingcome";

        System.out.println(a == b); // true
        System.out.println(a); // mingmingcome

        String c = new String("mingmingcome");
        String d = new String("mingmingcome");

        System.out.println(c.equals(d));

        Field value = c.getClass().getDeclaredField("value");
        value.setAccessible(true);
        char[] o = (char[])value.get(c);
        o[0] = 'L';
        o[1] = 'O';
        o[2] = 'V';
        o[3] = 'E';

        System.out.println(a.chars()); //LOVEmingcome
        System.out.println(b); //LOVEmingcome
        System.out.println(c); //LOVEmingcome
        System.out.println(d); //LOVEmingcome

    }
}
