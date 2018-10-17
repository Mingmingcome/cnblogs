package com.mingmingcome.designpattern.structural.decorator;

/** 
 * @className: HouseDecoratorImpl
 * @Description: 房子装饰者（具体装饰者角色）
 * @author: luhaoming
 * @date: 2018年9月28日 上午10:14:30
 */
public class HouseDecoratorImpl extends AttachedPropertiesDecorator{
	private String house = "有房";
	
	public HouseDecoratorImpl(Man man) {
		super(man);
	}
	
	public void addHouse() {
		System.out.print(house + " ");
	}
	
	@Override
	public void getManDesc() {
		super.getManDesc();
		addHouse();
	}
}
