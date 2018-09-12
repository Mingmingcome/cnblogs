package com.mingmingcome.designpattern.builder;

/** 
 * @className: Builder
 * @Description: 抽象建造者
 * 通常包含着创建产品的方法和返回一个完整产品的方法
 * @author: luhaoming
 * @date: 2018年9月11日 上午8:21:52
 */
public interface Builder {
	
	public void setPartA();
	
	public void setPartB();
	
	public void setPartC();
	
	public Product build();
}
