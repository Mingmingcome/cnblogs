package com.mingmingcome.designpattern.structural.adapter.defaultadapter;

/** 
 * @className: Operator
 * @Description: 操作者（在缺省适配器中不担任任何角色）
 * @author: luhaoming
 * @date: 2018年10月18日 上午10:23:11
 */
public class Operator {
	private SampleOperation sampleOperation;
	
	public void addOperation(SampleOperation sampleOperation) {
		this.sampleOperation = sampleOperation;
	}

	public void operation1() {
		sampleOperation.operation1();
	}

	public void operation2() {
		sampleOperation.operation2();
	}

	public void operation3() {
		sampleOperation.operation3();
	}

	public void operation4() {
		sampleOperation.operation4();
	}

	public void operation5() {
		sampleOperation.operation5();
	}
}
