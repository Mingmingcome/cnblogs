package com.mingmingcome.designpattern.proxy.staticproxy;

/** 
 * @ClassName: TestStaticProxy
 * @Description: 静态代理测试类
 * @author: luhaoming
 * @date: 2018年8月12日 下午3:22:19
 */
public class TestStaticProxy {

	public static void main(String[] args) {
		Subject subject = new RealSubject();
		Proxy proxy = new Proxy(subject);
		proxy.request();
	}

}
