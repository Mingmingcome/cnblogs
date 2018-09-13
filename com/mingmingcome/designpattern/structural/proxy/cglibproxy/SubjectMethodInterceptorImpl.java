package com.mingmingcome.designpattern.structural.proxy.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/** 
 * @ClassName: SubjectMethodInterceptorImpl
 * @Description: 实现了MethodInterceptor的方法拦截器
 * @author: luhaoming
 * @date: 2018年8月13日 下午7:10:08
 */
public class SubjectMethodInterceptorImpl implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("拦截前");
		// 在具体对象上调用原始类的方法
		Object ret = proxy.invokeSuper(obj, args);
		// Object ret1 = method.invoke(obj, args); // 用这种方式会发生死循环，因为方法会被拦截
		System.out.println("拦截后");
		return ret;
	}

}
