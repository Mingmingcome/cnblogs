package com.mingmingcome.designpattern.singleton;

/** 
 * @ClassName: LazyInitThreadUnsafeSingleton
 * @Description: 懒汉式线程不安全单例模式
 * @author: luhaoming
 * @date: 2018年8月8日 下午8:34:50
 */
public class LazyInitThreadUnsafeSingleton {
	private static LazyInitThreadUnsafeSingleton singleton;
	private LazyInitThreadUnsafeSingleton() {}	
	private static LazyInitThreadUnsafeSingleton getInstance() {
		if (singleton == null) {
			singleton = new LazyInitThreadUnsafeSingleton();
		}
		return singleton;
	}
}
