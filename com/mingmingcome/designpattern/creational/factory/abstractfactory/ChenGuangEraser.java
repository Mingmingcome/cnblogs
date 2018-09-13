package com.mingmingcome.designpattern.creational.factory.abstractfactory;

/** 
 * @className: ChenGuangEraser
 * @Description: 晨光橡皮
 * @author: luhaoming
 * @date: 2018年8月23日 下午8:11:57
 */
public class ChenGuangEraser implements Eraser {

	@Override
	public void erase() {
		System.out.println("用晨光橡皮擦除晨光铅笔画的图。");
	}

}
