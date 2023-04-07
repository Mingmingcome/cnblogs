package com.mingmingcome.designpattern.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Life 生命(对象结构)
 */
public class Life extends Pessimist {
    private List<Time> timeList = new ArrayList<>();

    public Life() {
        timeList.add(new Past());
        timeList.add(new Present());
        timeList.add(new Future());
    }

    public void visitTime(Man man) {
        for (Time time : timeList) {
            time.accept(man);
        }
    }
}
