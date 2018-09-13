package com.mingmingcome.designpattern.structural.proxy.staticproxy;

/** 
 * @ClassName: Proxy
 * @Description: 代理对象：代理对象角色内部包含对真实对象的引用，从而可以操作真实对象，
 * 同时代理对象提供域真实对象相同的接口以便任何时刻都能代替真实对象。
 * @author: luhaoming
 * @date: 2018年8月12日 下午3:00:10
 */
public class Proxy implements Subject {
	// 真实主题对象的引用
	private Subject subject;
	
	public Proxy(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	public void request() {
		preRequest();
		// 执行真实对象的方法
		subject.request();
		afterRequest();
	}
	
	private void preRequest() {
		System.out.println("这是在调用真实对象之前做的事情。。。");
	}
	
	private void afterRequest() {
		System.out.println("这是在调用真实对象之后做的事情。。。");
	}

}
