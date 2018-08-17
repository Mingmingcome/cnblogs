package com.mingmingcome.designpattern.proxy.cglibproxy;

/** 
 * @ClassName: RealSubject
 * @Description: 真实对象（没有实现任何接口）
 * @author: luhaoming
 * @date: 2018年8月12日 下午3:10:59
 */
public class RealSubject {
	
	public void request() {
		System.out.println("真实的请求");
	}

}
