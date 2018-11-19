package com.mingmingcome.designpattern.structural.bridge;

/** 
 * @className: CakeOrderRefinedAbstraction
 * @Description: 蛋糕订单类（扩充抽象类）
 * @author: luhaoming
 * @date: 2018年11月4日 下午6:28:35
 */
public class CakeOrderRefinedAbstraction extends OrderAbstraction {
	private int count;
	private String orderName = "蛋糕";

	public CakeOrderRefinedAbstraction(int count, FactoryImplementor factoryImplementor) {
		super(factoryImplementor);
		this.count = count;
	}

	@Override
	public void provide() {
		factoryImplementor.provide(count, orderName);
	}

}
