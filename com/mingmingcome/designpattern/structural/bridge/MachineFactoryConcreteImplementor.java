package com.mingmingcome.designpattern.structural.bridge;

/** 
 * @className: MachineFactoryConcreteImplementor
 * @Description: 机器实现类（具体实现类）
 * @author: luhaoming
 * @date: 2018年11月6日 下午4:54:20
 */
public class MachineFactoryConcreteImplementor implements FactoryImplementor {

	public void provide(int count, String orderName) {
		float time = (float)(count * 0.5);
		System.out.println("机器使用了" + time + "小时，完成了" + count + "份" + orderName);
	}

}
