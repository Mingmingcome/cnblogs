package com.mingmingcome.designpattern.factory.simplefactory;

/** 
 * @className: SimpleFactory
 * @Description: 简单工厂类
 * @author: luhaoming
 * @date: 2018年8月19日 下午4:54:20
 */
public class SimpleFactory {

	public static IOperation createOperation(String op) {
		IOperation operation = null;
		// 1、适用范围有限：byte、short、char、int及其包装类，还有Enum、String
//		switch(op){
//		case "+":
//			operation = new AddOperationImpl();
//		case "-":
//			operation = new SubOperationImpl();
//		case "*":
//			operation = new MulOperationImpl();
//		case "/":
//			operation = new DivOperationImpl();
//		}
		
		// 2、所有对象
		if ("+".equals(op)) {
			operation = new AddOperationImpl();
		} else if ("-".equals(op)) {
			operation = new SubOperationImpl();
		} else if ("*".equals(op)) {
			operation = new MulOperationImpl();
		} else if ("/".equals(op)) {
			operation = new DivOperationImpl();
		}
		
		return operation;
	}
}
