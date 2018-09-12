package com.mingmingcome.designpattern.builder;

/** 
 * @className: NutritionFacts
 * @Description: 营养成分类（使用重叠构造器）
 * 一个类表示包装食品外面的显示的营养成分标签。这些标签中有几个域是必须的：每份的含量、每罐的含量以及每份的卡路里，
 * 还有超过20个可选域：总脂肪量、饱和脂肪量、转化脂肪、胆固醇、钠等等。
 * @author: luhaoming
 * @date: 2018年9月11日 下午7:15:59
 */
public class NutritionFacts {
	private final int servingSize;  //(ml)            required
	private final int servings;     //(per container) required
	private final int calories;     //                optional  
	private final int fat;          //(g)             optional  
	private final int sodium;       //(mg)            optional
	private final int carbohydrate; //(g)             optional  
	public NutritionFacts(int servingSize, int servings) {
		this(servingSize, servings, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories) {
		this(servingSize, servings, calories, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories, int fat) {
		this(servingSize, servings, calories, fat, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
		this(servingSize, servings, calories, fat, sodium, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
		this.servingSize = servingSize;
		this.servings = servings;
		this.calories = calories;
		this.fat = fat;
		this.sodium = sodium;
		this.carbohydrate = carbohydrate;
	}
	@Override
	public String toString() {
		return "NutritionFacts [servingSize=" + servingSize + ", servings=" + servings + ", calories=" + calories
				+ ", fat=" + fat + ", sodium=" + sodium + ", carbohydrate=" + carbohydrate + "]";
	}
}
