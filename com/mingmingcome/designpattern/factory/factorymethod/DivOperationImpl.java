package com.mingmingcome.designpattern.factory.factorymethod;

/** 
 * @className: DivOperationImpl
 * @Description: 减运算实现类
 * @author: luhaoming
 * @date: 2018年8月19日 下午4:52:48
 */
public class DivOperationImpl implements IOperation {

	@Override
	public int getResult(int a, int b) {
		return a / b;
	}

}
