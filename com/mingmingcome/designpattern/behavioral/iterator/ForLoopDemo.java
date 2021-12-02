package com.mingmingcome.designpattern.behavioral.iterator;

import java.util.*;

/**
 * @who luhaoming
 * @when 2021/12/1 12:55
 * @what for循环
 */
public class ForLoopDemo {

    public static void main(String[] args) {
        arrayForLoop();
        listForLoop();
    }

    private static void listForLoop() {
        List<String> list = new ArrayList<>();
        list.add("ming");
        list.add("ming");
        list.add("come");
        for(String s : list) {
            System.out.println(s);
        }
    }

    private static void arrayForLoop() {
        int[] ints = {1,2,3,4,5,6,7,8,9,10};
        for (int i : ints) {
            System.out.println(i);
        }
    }

}
