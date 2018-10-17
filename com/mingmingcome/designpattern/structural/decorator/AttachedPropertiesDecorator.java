package com.mingmingcome.designpattern.structural.decorator;

/** 
 * @className: AttachedPropertiesDecorator
 * @Description: 附属属性装饰者（抽象装饰者角色）
 * @author: luhaoming
 * @date: 2018年9月28日 上午9:19:13
 */
public abstract class AttachedPropertiesDecorator implements Man{
	private Man man;
	
	public AttachedPropertiesDecorator(Man man) {
		this.man = man;
	}
	
	public void getManDesc() {
		man.getManDesc();
	}
}
