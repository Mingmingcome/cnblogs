package com.mingmingcome.designpattern.creational.factory.simplefactory;

/** 
 * @className: SubOperationImpl
 * @Description: 减运算实现类
 * @author: luhaoming
 * @date: 2018年8月19日 下午4:48:55
 */
public class SubOperationImpl implements IOperation {
	
	@Override
	public int getResult(int a, int b) {
		return a - b;
	}
}
