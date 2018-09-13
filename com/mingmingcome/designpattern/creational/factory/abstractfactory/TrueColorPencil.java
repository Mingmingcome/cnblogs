package com.mingmingcome.designpattern.creational.factory.abstractfactory;

/** 
 * @className: TrueColorPencil
 * @Description: 真彩铅笔
 * @author: luhaoming
 * @date: 2018年8月23日 下午8:02:17
 */
public class TrueColorPencil implements Pencil {

	@Override
	public void draw() {
		System.out.println("用真彩铅笔画图。");
	}

}
