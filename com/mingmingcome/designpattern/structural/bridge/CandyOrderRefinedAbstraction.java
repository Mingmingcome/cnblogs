package com.mingmingcome.designpattern.structural.bridge;

/** 
 * @className: CandyOrderRefinedAbstraction
 * @Description: 糖果订单类（扩充抽象类）
 * @author: luhaoming
 * @date: 2018年11月4日 下午6:28:35
 */
public class CandyOrderRefinedAbstraction extends OrderAbstraction {
	private int count;
	private String orderName = "糖果";

	public CandyOrderRefinedAbstraction(int count, FactoryImplementor factoryImplementor) {
		super(factoryImplementor);
		this.count = count;
	}

	@Override
	public void provide() {
		factoryImplementor.provide(count, orderName);
	}

}
