package com.mingmingcome.designpattern.creational.prototype;

/**
 * @ClassName PrototypePatternTest
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/7/23 8:43
 */
public class PrototypePatternTest{
    public static void main(String[] args) throws CloneNotSupportedException {
        Movie moviePrototype = new Movie();
        Movie movie = moviePrototype.clone();
        System.out.println(movie);

        Album albumPrototype = new Album();
        Album album = albumPrototype.clone();
        System.out.println(album);

        Show showPrototype = new Show();
        Show show = showPrototype.clone();
        System.out.println(show);
    }
}
