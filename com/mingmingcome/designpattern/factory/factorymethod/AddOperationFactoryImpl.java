package com.mingmingcome.designpattern.factory.factorymethod;

/** 
 * @className: AddOperationFactoryImpl
 * @Description: 加法运算工厂
 * @author: luhaoming
 * @date: 2018年8月22日 下午2:25:55
 */
public class AddOperationFactoryImpl implements IOperationFactory {

	@Override
	public IOperation createOperation() {
		return new AddOperationImpl();
	}

}
