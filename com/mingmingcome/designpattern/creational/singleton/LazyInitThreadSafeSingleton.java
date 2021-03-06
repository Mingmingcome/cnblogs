package com.mingmingcome.designpattern.creational.singleton;

/**
 * @ClassName: LazyInitThreadSafeSingleton
 * @Description: 懒汉式线程安全单例模式
 * @author: luhaoming
 * @date: 2018年8月8日 下午9:02:44
 */
public class LazyInitThreadSafeSingleton {
	private static LazyInitThreadSafeSingleton singleton;
	private LazyInitThreadSafeSingleton() {}	
	public static synchronized LazyInitThreadSafeSingleton getInstance() {
		if (singleton == null) {
			singleton = new LazyInitThreadSafeSingleton();
		}
		return singleton;
	}
}
