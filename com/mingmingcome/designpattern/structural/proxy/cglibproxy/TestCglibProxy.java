package com.mingmingcome.designpattern.structural.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;

/** 
 * @ClassName: TestCglibProxy
 * @Description: 测试：动态生成代理实例，调用代理实例方法
 * @author: luhaoming
 * @date: 2018年8月13日 下午7:43:41
 */
public class TestCglibProxy {

	public static void main(String[] args) {
		// 动态代理增强器
		Enhancer enhancer = new Enhancer();
		// 设置超类，可以是没有实现任何接口的具体类，也可以是接口。
		// 如果是接口的话，setInterfaces会被调用。
		enhancer.setSuperclass(RealSubject.class);
		// 设置回调函数（MethodInterceptor接口是继承了Callback接口的）
		enhancer.setCallback(new SubjectMethodInterceptorImpl());
		// 动态生成的代理实例
		RealSubject proxy = (RealSubject)enhancer.create();
		// 调用代理实例的方法
		proxy.request();
		
	}

}
