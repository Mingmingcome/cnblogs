package com.mingmingcome.designpattern.structural.bridge;

/** 
 * @className: HandworkFactoryConcreteImplementor
 * @Description: 手工实现类（具体实现类）
 * @author: luhaoming
 * @date: 2018年11月6日 下午4:54:20
 */
public class HandworkFactoryConcreteImplementor implements FactoryImplementor {

	public void provide(int count, String orderName) {
		float time = (float)(count * 1);
		System.out.println("手工使用了" + time + "小时，完成了" + count + "份" + orderName);
	}

}
