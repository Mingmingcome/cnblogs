package com.mingmingcome.designpattern.creational.factory.abstractfactory;

/** 
 * @className: ChenGuangFactory
 * @Description: 真彩工厂
 * @author: luhaoming
 * @date: 2018年8月24日 上午8:45:59
 */
public class ChenGuangFactory implements AbstractFactory {

	@Override
	public Pencil createPencil() {
		return new ChenGuangPencil();
	}

	@Override
	public Eraser createEraser() {
		return new ChenGuangEraser();
	}

}
