package com.mingmingcome.designpattern.creational.factory.abstractfactory;

/** 
 * @className: ChenGuangPencil
 * @Description: 晨光铅笔
 * @author: luhaoming
 * @date: 2018年8月23日 下午8:00:19
 */
public class ChenGuangPencil implements Pencil {

	@Override
	public void draw() {
		System.out.println("用晨光铅笔画图。");
	}

}
