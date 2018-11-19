package com.mingmingcome.designpattern.structural.bridge;

/** 
 * @className: FactoryImplementor
 * @Description: 订单实现类（实现类接口）
 * @author: luhaoming
 * @date: 2018年11月4日 下午6:25:25
 */
public interface FactoryImplementor {
	public void provide(int count, String orderName);
}
