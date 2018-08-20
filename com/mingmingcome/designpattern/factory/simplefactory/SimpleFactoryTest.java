package com.mingmingcome.designpattern.factory.simplefactory;

/** 
 * @className: SimpleFactoryTest
 * @Description: 简单工厂模式测试类
 * @author: luhaoming
 * @date: 2018年8月19日 下午5:05:36
 */
public class SimpleFactoryTest {
	
	public static void main(String[] args) {
		int a = 999, b = 888;
		// 加
		IOperation operation = SimpleFactory.createOperation("+");
		int add = operation.getResult(a, b);
		System.out.println("a '+' b:" + add); // a '+' b:1887
		// 减
		operation = SimpleFactory.createOperation("-");
		operation.getResult(a, b);
		int sub = operation.getResult(a, b);
		System.out.println("a '-' b:" + sub); // a '-' b:111
		// 乘
		operation = SimpleFactory.createOperation("*");
		operation.getResult(a, b);
		int mul = operation.getResult(a, b);
		System.out.println("a '*' b:" + mul); // a '*' b:887112
		// 除
		operation = SimpleFactory.createOperation("/");
		operation.getResult(a, b);
		int div = operation.getResult(a, b);
		System.out.println("a '/' b:" + div); // a '/' b:1
	}
}
