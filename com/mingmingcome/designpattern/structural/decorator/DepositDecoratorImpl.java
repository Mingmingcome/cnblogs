package com.mingmingcome.designpattern.structural.decorator;

/** 
 * @className: DepositDecoratorImpl
 * @Description: 存款装饰者（具体装饰者角色）
 * @author: luhaoming
 * @date: 2018年9月28日 上午10:40:17
 */
public class DepositDecoratorImpl extends AttachedPropertiesDecorator{
	private String deposit = "有存款";
	
	public DepositDecoratorImpl(Man man) {
		super(man);
	}
	
	public void addDeposit() {
		System.out.print(deposit + " ");
	}
	
	@Override
	public void getManDesc() {
		super.getManDesc();
		addDeposit();
	}
}
