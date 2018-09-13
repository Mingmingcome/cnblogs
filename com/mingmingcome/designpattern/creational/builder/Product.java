package com.mingmingcome.designpattern.creational.builder;

/** 
 * @className: Product
 * @Description: 产品
 * @author: luhaoming
 * @date: 2018年9月11日 上午8:26:21
 */
public class Product {
	private String partA;
	
	private String partB;
	
	private String partC;

	public String getPartA() {
		return partA;
	}

	public void setPartA(String partA) {
		this.partA = partA;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getPartC() {
		return partC;
	}

	public void setPartC(String partC) {
		this.partC = partC;
	}

	@Override
	public String toString() {
		return "Product [partA=" + partA + ", partB=" + partB + ", partC=" + partC + "]";
	}
	
}
