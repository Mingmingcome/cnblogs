package com.mingmingcome.designpattern.factory.factorymethod;

/** 
 * @className: FactoryMethodTest
 * @Description: 工厂方法模式测试类（即客户端）
 * @author: luhaoming
 * @date: 2018年8月22日 下午2:34:13
 */
public class FactoryMethodTest {
	
	public static void main(String[] args) {
		int a = 999, b = 888;
		// 加
		// 创建具体工厂
		IOperationFactory operationFactory = new AddOperationFactoryImpl();
		// 创建具体产品
		IOperation operation = operationFactory.createOperation();
		// 调用具体产品的功能
		int result = operation.getResult(a, b); // 1887
		System.out.println(result);
		
		// 减
		operationFactory = new SubOperationFactoryImpl();
		operation = operationFactory.createOperation();
		result = operation.getResult(a, b); // 111
		System.out.println(result);
		
		// 乘
		operationFactory = new MulOperationFactoryImpl();
		operation = operationFactory.createOperation();
		result = operation.getResult(a, b); // 887112
		System.out.println(result);
		
		// 除
		operationFactory = new DivOperationFactoryImpl();
		operation = operationFactory.createOperation();
		result = operation.getResult(a, b); // 1
		System.out.println(result);
		
	}
	
}
