package com.mingmingcome.designpattern.factory.simplefactory;

/** 
 * @className: AddOperationImpl
 * @Description: 加运算实现类
 * @author: luhaoming
 * @date: 2018年8月19日 下午4:46:25
 */
public class AddOperationImpl implements IOperation{

	@Override
	public int getResult(int a, int b) {
		return a + b;
	}

}
