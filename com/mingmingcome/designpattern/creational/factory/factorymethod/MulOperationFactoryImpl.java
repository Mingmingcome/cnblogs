package com.mingmingcome.designpattern.creational.factory.factorymethod;

/** 
 * @className: MulOperationFactoryImpl
 * @Description: 乘法运算工厂
 * @author: luhaoming
 * @date: 2018年8月22日 下午2:29:28
 */
public class MulOperationFactoryImpl implements IOperationFactory {

	@Override
	public IOperation createOperation() {
		return new MulOperationImpl();
	}

}
