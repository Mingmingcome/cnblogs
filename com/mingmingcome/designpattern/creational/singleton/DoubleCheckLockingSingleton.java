package com.mingmingcome.designpattern.creational.singleton;

/**
 * @ClassName: DoubleCheckSingleton
 * @Description: 双重锁定单例模式(线程安全)
 * @author: luhaoming
 * @date: 2018年8月8日 下午9:14:32
 */
public class DoubleCheckLockingSingleton {
	private static DoubleCheckLockingSingleton singleton;
	private DoubleCheckLockingSingleton() {}
	public static DoubleCheckLockingSingleton getInstance() {
		if (singleton == null) {
			synchronized(DoubleCheckLockingSingleton.class) {
				if (singleton == null) {
					singleton = new DoubleCheckLockingSingleton();
				}
			}
		}
		return singleton;
	}
	
}
