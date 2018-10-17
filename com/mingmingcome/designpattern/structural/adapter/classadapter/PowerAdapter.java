package com.mingmingcome.designpattern.structural.adapter.classadapter;

/** 
 * @className: PowerAdapter
 * @Description: 电源适配器（适配器角色）
 * @author: luhaoming
 * @date: 2018年10月17日 下午2:11:34
 */
public class PowerAdapter extends PowerAdaptee implements PowerTarget{
	
	@Override
	public int output5V() {
		int output = output220V();
		System.out.println("电源适配器开始工作，此时输出电压是：" + output);
		output = output/44;
		System.out.println("电源适配器工作完成，此时输出电压是：" + output);
		return output;
	}
	
}
