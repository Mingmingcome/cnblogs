package com.mingmingcome.designpattern.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @who luhaoming
 * @when 2020/11/29 20:24
 * @what 时光倒流相框
 */
public class Frame {
    private List<I.Photo> photoList = new ArrayList<>();

    public I.Photo getPhoto(int i) {
        return photoList.get(i);
    }

    public void add(I.Photo photo) {
        photoList.add(photo);
    }

}
