package com.mingmingcome.designpattern.singleton;

/**
 * @ClassName: EagerSingleton
 * @Description: 饿汉式单例模式(线程安全)
 * @author: luhaoming
 * @date: 2018年8月8日 下午9:04:18
 */
public class EagerSingleton {
	private static EagerSingleton singleton = new EagerSingleton();
	private EagerSingleton() {}
	public static EagerSingleton getInstance() {
		return singleton;
	}
}
