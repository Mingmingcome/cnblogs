package com.mingmingcome.designpattern.structural.decorator;

/** 
 * @className: QualityDecoratorImpl
 * @Description: 品质装饰者（具体装饰者角色）
 * @author: luhaoming
 * @date: 2018年9月28日 上午10:50:00
 */
public class QualityDecoratorImpl extends AttachedPropertiesDecorator{
	private String quality = "有好品质";
	
	public QualityDecoratorImpl(Man man) {
		super(man);
	}
	
	public void addQuality() {
		System.out.print(quality + " ");
	}
	
	@Override
	public void getManDesc() {
		super.getManDesc();
		addQuality();
	}
}
