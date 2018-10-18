package com.mingmingcome.designpattern.structural.adapter.objectadapter;

/** 
 * @className: ClassAdapterPatternTest
 * @Description: 适配器模式测试类
 * @author: luhaoming
 * @date: 2018年10月17日 下午2:29:20
 */
public class ObjectAdapterPatternTest {
	
	public static void main(String[] args) {
		PowerTarget target = new PowerAdapter(new PowerAdaptee());
		target.output5V();
	}
}
