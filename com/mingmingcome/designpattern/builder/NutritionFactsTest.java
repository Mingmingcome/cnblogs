package com.mingmingcome.designpattern.builder;

/** 
 * @className: NutritionFactsTest
 * @Description: 测试含有很多初始化参数的营养成分类
 * @author: luhaoming
 * @date: 2018年9月11日 下午8:04:24
 */
public class NutritionFactsTest {

	public static void main(String[] args) {
		
		NutritionFacts cocaCola1 = new NutritionFacts(240, 8, 100, 0, 35, 27);
		System.out.println(cocaCola1);
		
		NutritionFactsWithBuilder cocaCola2 = new NutritionFactsWithBuilder.Builder(240, 8)
				.calories(100).sodium(35).carbohydrate(27).build();
		System.out.println(cocaCola2);
		
	}
}
