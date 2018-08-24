package com.mingmingcome.designpattern.factory.abstractfactory;

/** 
 * @className: AbstractFactoryTest
 * @Description: 抽象工厂模式测试类
 * @author: luhaoming
 * @date: 2018年8月24日 上午9:03:21
 */
public class AbstractFactoryTest {

	public static void main(String[] args) {
		// 1、晨光
		AbstractFactory factory = new ChenGuangFactory();
		Pencil pencil = factory.createPencil();
		pencil.draw(); // 用晨光铅笔画图。
		Eraser eraser = factory.createEraser();
		eraser.erase(); // 用晨光橡皮擦除晨光铅笔画的图。
		
		// 2、真彩
		AbstractFactory factoryT = new TrueColorFactory();
		Pencil pencilT = factoryT.createPencil();
		pencilT.draw(); // 用真彩铅笔画图。
		Eraser eraserT = factoryT.createEraser();
		eraserT.erase(); // 用真彩橡皮擦除真彩铅笔画的图。
	}

}
