package com.mingmingcome.designpattern.structural.decorator;

import java.lang.reflect.Field;

/** 
 * @className: NormalMan
 * @Description: 普通男人（具体构件角色）
 * @author: luhaoming
 * @date: 2018年9月28日 上午9:10:40
 */
public class NormalMan implements Man{
	private String name = null;
	
	public NormalMan(String name) {
		this.name = name;
	}
	
	@Override
	public void getManDesc() {
		System.out.print(name + ": ");
	}
}
