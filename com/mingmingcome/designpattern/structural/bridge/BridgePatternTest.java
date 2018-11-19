package com.mingmingcome.designpattern.structural.bridge;

/** 
 * @className: BridgePatternTest
 * @Description: 桥接模式测试类
 * @author: luhaoming
 * @date: 2018年11月6日 下午5:11:54
 */
public class BridgePatternTest {

	public static void main(String[] args) {
		// 手工蛋糕订单
		OrderAbstraction handworkCake = 
				new CakeOrderRefinedAbstraction(10, 
						new HandworkFactoryConcreteImplementor());
		// 机器蛋糕订单
		OrderAbstraction machineCake = 
				new CakeOrderRefinedAbstraction(10, 
						new MachineFactoryConcreteImplementor());
		// 手工糖果订单
		OrderAbstraction handworkCandy = 
				new CandyOrderRefinedAbstraction(10, 
						new HandworkFactoryConcreteImplementor());
		// 机器糖果订单
		OrderAbstraction machineCandy = 
				new CandyOrderRefinedAbstraction(10, 
						new MachineFactoryConcreteImplementor());

		handworkCake.provide();
		machineCake.provide();
		handworkCandy.provide();
		machineCandy.provide();
	}
}
