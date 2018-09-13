package com.mingmingcome.designpattern.structural.proxy.dynamicproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/** 
 * @ClassName: TestDynamicProxy
 * @Description: TODO
 * @author: luhaoming
 * @date: 2018年8月12日 下午6:59:23
 */
public class TestDynamicProxy {

	public static void main(String[] args) {
		// the class loader to define the proxy class
		// 定义代理类的类加载器
		// 测试类中Subjec、RealSuject、TestDynamicProxy的类加载器都是一样的，不知道是否需要明确哪个类加载器
		ClassLoader loader = Subject.class.getClassLoader();

		// the list of interfaces for the proxy class to implement
		// 代理类要实现的接口列表
		Class<?>[] interfaces = RealSubject.class.getInterfaces();
		
		// the invocation handler to dispatch method invocations to
		// 分发方法调用的调用处理器
		InvocationHandler handler = new SubjectInvocationHandler(new RealSubject());
		
		// 生产代理类
		Subject proxy = (Subject)Proxy.newProxyInstance(loader, interfaces, handler);
		
		// 以下是Proxy.newProxyInstance分解步骤
		/* 这太麻烦了。。。
		// 1、
		Class<?> c = Proxy.getProxyClass(loader, interfaces);
		Constructor<?> ct = null;
		Subject proxy = null;
		try {
			// 2、
			ct = c.getConstructor(new Class[]{InvocationHandler.class});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		try {
			// 3、
			proxy =(Subject) ct.newInstance(new Object[]{handler});
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		*/
		
		// 调用真实对象方法
		proxy.request();
	}

}
