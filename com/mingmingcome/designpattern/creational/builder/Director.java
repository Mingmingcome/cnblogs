package com.mingmingcome.designpattern.creational.builder;

/** 
 * @className: Director
 * @Description: 指挥者
 * 拥有一个建造者，通过建造方法控制建造流程。
 * @author: luhaoming
 * @date: 2018年9月11日 上午8:19:50
 */
public class Director {
	private Builder builder;
	
	// 指定建造者
	public Director(Builder builder) {
		this.builder = builder;
	}
	
	public Product construct() {
		builder.setPartA();
		builder.setPartB();
		builder.setPartC();
		return builder.build();
	}
}
