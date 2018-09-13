package com.mingmingcome.designpattern.creational.builder;

/** 
 * @className: ConcreteBuilder
 * @Description: 具体建造者（可以有多个）
 * 为抽象建造者提供实现，是一个可以构造其他对象的对象。
 * 通过构造和组装来创建对象。
 * @author: luhaoming
 * @date: 2018年9月11日 上午8:23:28
 */
public class ConcreteBuilder implements Builder {
	
	private Product product;
	
	public ConcreteBuilder() {
		product = new Product();
	}

	@Override
	public void setPartA() {
		product.setPartA("Part A");
	}

	@Override
	public void setPartB() {
		product.setPartB("Part B");		
	}

	@Override
	public void setPartC() {
		product.setPartC("Part C");
	}

	@Override
	public Product build() {		
		return product;
	}

}
