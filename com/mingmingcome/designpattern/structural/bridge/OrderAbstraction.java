package com.mingmingcome.designpattern.structural.bridge;

/** 
 * @className: OrderAbstraction
 * @Description: 订单（抽象类角色）
 * @author: luhaoming
 * @date: 2018年11月4日 下午5:37:04
 */
public abstract class OrderAbstraction {
	protected FactoryImplementor factoryImplementor;
	
	public OrderAbstraction(FactoryImplementor factoryImplementor) {
		this.factoryImplementor = factoryImplementor;
	}
	
	public abstract void provide();
}
