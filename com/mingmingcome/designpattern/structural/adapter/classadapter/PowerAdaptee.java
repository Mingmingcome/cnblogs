package com.mingmingcome.designpattern.structural.adapter.classadapter;

/** 
 * @className: OutputAdaptee
 * @Description: 电源（适配者角色）
 * @author: luhaoming
 * @date: 2018年10月17日 上午11:40:57
 */
public class PowerAdaptee {
	private int output =  220;
	public int output220V() {
		System.out.println("电源输出电压：" + output);
		return output;
	}
}
