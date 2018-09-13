package com.mingmingcome.designpattern.creational.factory.factorymethod;

/** 
 * @className: SubOperationFactoryImpl
 * @Description: 除法运算工厂
 * @author: luhaoming
 * @date: 2018年8月22日 下午2:27:56
 */
public class SubOperationFactoryImpl implements IOperationFactory {

	@Override
	public IOperation createOperation() {
		return new SubOperationImpl();
	}

}
