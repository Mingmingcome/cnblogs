package com.mingmingcome.designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** 
 * @ClassName: Proxy
 * @Description: TODO
 * @author: luhaoming
 * @date: 2018年8月12日 下午4:26:45
 */
public class SubjectInvocationHandler implements InvocationHandler {
	
	// 这里真实对象不再是具体接口，而是Object（实现了任意接口的所有类）
	private Object object;
	
	public SubjectInvocationHandler(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 模仿日志的功能
		System.out.println("调用前");
		method.invoke(object, args);
		// 模仿日志的功能
		System.out.println("调用后");
		return null;
	}

}
