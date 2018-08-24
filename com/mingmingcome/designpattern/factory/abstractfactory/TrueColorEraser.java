package com.mingmingcome.designpattern.factory.abstractfactory;

/** 
 * @className: TrueColorEraser
 * @Description: 真彩橡皮
 * @author: luhaoming
 * @date: 2018年8月23日 下午8:14:54
 */
public class TrueColorEraser implements Eraser {

	@Override
	public void erase() {
		System.out.println("用真彩橡皮擦除真彩铅笔画的图。");
	}

}
