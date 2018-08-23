package com.mingmingcome.designpattern.factory.factorymethod;

/** 
 * @className: DivOperationFactoryImpl
 * @Description: 除法运算工厂
 * @author: luhaoming
 * @date: 2018年8月22日 下午2:30:02
 */
public class DivOperationFactoryImpl implements IOperationFactory {

	@Override
	public IOperation createOperation() {
		return new DivOperationImpl();
	}

}
