package com.mingmingcome.designpattern.structural.adapter.classadapter;

/** 
 * @className: ClassAdapterPatternTest
 * @Description: 适配器模式测试类
 * @author: luhaoming
 * @date: 2018年10月17日 下午2:29:20
 */
public class ClassAdapterPatternTest {
	
	public static void main(String[] args) {
		PowerTarget target = new PowerAdapter();
		target.output5V();
	}
}
