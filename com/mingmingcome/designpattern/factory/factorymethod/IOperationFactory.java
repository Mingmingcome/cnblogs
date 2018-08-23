package com.mingmingcome.designpattern.factory.factorymethod;

/** 
 * @className: IOperationFactory
 * @Description: 抽象工厂类
 * @author: luhaoming
 * @date: 2018年8月22日 下午2:22:27
 */
public interface IOperationFactory {
	public IOperation createOperation();
}
