package com.mingmingcome.designpattern.singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * @ClassName: TestSingleton
 * @Description: 单例模式测试类
 * @author: luhaoming
 * @date: 2018年8月9日 上午8:16:36
 */
public class TestSingleton {

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
	
		// 线程池
		ExecutorService exec0 = Executors.newCachedThreadPool();
		
		// 装Singleton的容器
		Set<LazyInitThreadUnsafeSingleton> set0 = Collections.synchronizedSet(new HashSet<LazyInitThreadUnsafeSingleton>());
		Set<LazyInitThreadSafeSingleton> set1 = Collections.synchronizedSet(new HashSet<LazyInitThreadSafeSingleton>());
		Set<EagerSingleton> set2 = Collections.synchronizedSet(new HashSet<EagerSingleton>());
		Set<DoubleCheckLockingSingleton> set3 = Collections.synchronizedSet(new HashSet<DoubleCheckLockingSingleton>());
		Set<StaticInnerClassSingleton> set4 = Collections.synchronizedSet(new HashSet<StaticInnerClassSingleton>());
		Set<EnumSingleton> set5 = Collections.synchronizedSet(new HashSet<EnumSingleton>());
		
		// 可重用的同步多个任务
		CyclicBarrier cb0 = new CyclicBarrier(1000);
		
		// 不可重用的同步多个任务
		CountDownLatch cdl = new CountDownLatch(1000);
		
		// 懒汉式线程不安全单例模式
		for (int i = 0; i < 1000; i++) {
			cdl.countDown();
			exec0.execute(new Runnable() {
				
				@Override
				public void run() {
				try {
					cdl.await();
					set0.add(LazyInitThreadUnsafeSingleton.getInstance());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			});
		}
		
		// 懒汉式线程安全单例模式
		for (int i = 0; i < 1000; i++) {
			exec0.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						cb0.await();
						set1.add(LazyInitThreadSafeSingleton.getInstance()); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		
		// 饿汉式单例模式
		for (int i = 0; i < 1000; i++) {
			exec0.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						cb0.await();
						set2.add(EagerSingleton.getInstance()); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		
		// 双重锁定单例模式
		for (int i = 0; i < 1000; i++) {
			exec0.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						cb0.await();
						set3.add(DoubleCheckLockingSingleton.getInstance()); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		
		// 静态内部类单例模式
		for (int i = 0; i < 1000; i++) {
			exec0.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						cb0.await();
						set4.add(StaticInnerClassSingleton.getInstance()); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		
		// 枚举单例模式
		for (int i = 0; i < 1000; i++) {
			exec0.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						cb0.await();
						set4.add(StaticInnerClassSingleton.getInstance()); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		
		Thread.sleep(2000);
		System.out.println("懒汉式线程不安全单例模式");
		for (LazyInitThreadUnsafeSingleton s : set0) {
			System.out.println(s);
		}
		System.out.println("懒汉式线程安全单例模式");
		for (LazyInitThreadSafeSingleton s : set1) {
			System.out.println(s);
		}
		System.out.println("饿汉式单例模式");
		for (EagerSingleton s : set2) {
			System.out.println(s);
		}
		System.out.println("双重锁定单例模式");
		for (DoubleCheckLockingSingleton s : set3) {
			System.out.println(s);
		}
		System.out.println("静态内部类单例模式");
		for (StaticInnerClassSingleton s : set4) {
			System.out.println(s);
		}
		System.out.println("枚举单例模式");
		for (EnumSingleton s : set5) {
			System.out.println(s);
		}	
		
		exec0.shutdown();
	}

}
