package com.mingmingcome.designpattern.structural.proxy.dynamicproxy;

/** 
 * @ClassName: RealSubject
 * @Description: 真实对象
 * @author: luhaoming
 * @date: 2018年8月12日 下午3:10:59
 */
public class RealSubject implements Subject {
	
	@Override
	public void request() {
		System.out.println("真实的请求");
	}

}
