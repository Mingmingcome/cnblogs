package com.mingmingcome.designpattern.structural.decorator;

/** 
 * @className: CarDecoratorImpl
 * @Description: 小车装饰者（具体装饰者角色）
 * @author: luhaoming
 * @date: 2018年9月28日 上午10:06:36
 */
public class CarDecoratorImpl extends AttachedPropertiesDecorator{
	private String car = "有车";
	
	public CarDecoratorImpl(Man man) {
		super(man);
	}
	
	public void addCar() {
		System.out.print(car + " ");
	}
	
	@Override
	public void getManDesc() {
		super.getManDesc();
		addCar();
	}
}
