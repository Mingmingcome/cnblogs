package com.mingmingcome.designpattern.factory.abstractfactory;

/** 
 * @className: AbstractFactory
 * @Description: 抽象工厂接口
 * @author: luhaoming
 * @date: 2018年8月23日 下午8:17:41
 */
public interface AbstractFactory {
	public Pencil createPencil();
	public Eraser createEraser();
}
