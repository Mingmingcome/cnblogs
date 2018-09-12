package com.mingmingcome.designpattern.builder;

/** 
 * @className: BuilderTest
 * @Description: 建造模式测试类
 * @author: luhaoming
 * @date: 2018年9月11日 下午12:24:29
 */
public class BuilderTest {

	public static void main(String[] args) {
		// 建造者
		Builder builder  = new ConcreteBuilder();
		// 指挥者
		Director director = new Director(builder);
		// 指挥者创建产品返回产品
		Product prod = director.construct();
		
		System.out.println(prod);
	}

}
