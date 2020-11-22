package com.mingmingcome.designpattern.creational.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PrototypeFactory
 * @Description TODO
 * @Author luhaoming
 * @Date 2019/7/27 17:59
 */
public class PrototypeFactory {
    public static class ModelType {
        public static final String MOVIE = "movie";
        public static final String ALBUM = "album";
        public static final String SHOW = "show";
    }

    private static Map<String, PrototypeCapable> prototypes = new HashMap<String, PrototypeCapable>();

    static {
        prototypes.put(ModelType.MOVIE, new Movie());
        prototypes.put(ModelType.ALBUM, new Album());
        prototypes.put(ModelType.SHOW, new Show());
    }

    public static PrototypeCapable getInstance(String s) throws CloneNotSupportedException {
        return prototypes.get(s).clone();
    }
}
