package com.mingmingcome.designpattern.creational.factory.simplefactory;

/** 
 * @className: MulOperationImpl
 * @Description: 乘运算实现类
 * @author: luhaoming
 * @date: 2018年8月19日 下午4:52:04
 */
public class MulOperationImpl implements IOperation {

	@Override
	public int getResult(int a, int b) {
		return a * b;
	}

}
