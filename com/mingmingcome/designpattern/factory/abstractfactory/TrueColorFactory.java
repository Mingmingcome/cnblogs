package com.mingmingcome.designpattern.factory.abstractfactory;

/** 
 * @className: TrueColorFactory
 * @Description: 真彩工厂
 * @author: luhaoming
 * @date: 2018年8月24日 上午8:46:24
 */
public class TrueColorFactory implements AbstractFactory{

	@Override
	public Pencil createPencil() {
		return new TrueColorPencil();
	}

	@Override
	public Eraser createEraser() {
		return new TrueColorEraser();
	}

}
