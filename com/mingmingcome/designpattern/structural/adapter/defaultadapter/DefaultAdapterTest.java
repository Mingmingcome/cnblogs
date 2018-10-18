package com.mingmingcome.designpattern.structural.adapter.defaultadapter;

/** 
 * @className: DefaultAdapterTest
 * @Description: 缺省适配器模式测试类
 * @author: luhaoming
 * @date: 2018年10月18日 上午10:32:04
 */
public class DefaultAdapterTest {

	public static void main(String[] args) {
		
		// 1、原来要实现所有操作类的操作
		Operator operator1 = new Operator();
		operator1.addOperation(new SampleOperation() {

			@Override
			public void operation1() {}

			@Override
			public void operation2() {
				System.out.println("操作2");
			}

			@Override
			public void operation3() {}

			@Override
			public void operation4() {}

			@Override
			public void operation5() {}
			
		});
		operator1.operation2();
		
		// 2、使用缺省适配器只需要实现需要用到的接口方法
		Operator operator2 = new Operator();
		operator2.addOperation(new DefaultAdapter() {
			
			@Override
			public void operation2() {
				System.out.println("操作2");
			}
		});
		operator2.operation2();
	}
}
