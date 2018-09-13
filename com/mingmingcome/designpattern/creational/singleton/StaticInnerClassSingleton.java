package com.mingmingcome.designpattern.creational.singleton;

/**
 * @ClassName: StaticInnerClassSingleton
 * @Description: 静态内部类单例模式(线程安全)
 * @author: luhaoming
 * @date: 2018年8月8日 下午9:09:02
 */
public class StaticInnerClassSingleton {
	private static class SingletonHolder {
		private static StaticInnerClassSingleton singleton = new StaticInnerClassSingleton();
	}
	private StaticInnerClassSingleton() {}
	public static StaticInnerClassSingleton getInstance() {
		return SingletonHolder.singleton;
	}
}
