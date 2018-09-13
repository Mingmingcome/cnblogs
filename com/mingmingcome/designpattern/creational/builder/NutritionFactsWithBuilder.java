package com.mingmingcome.designpattern.creational.builder;

/** 
 * @className: NutritionFactsWithBuilder
 * @Description: 营养成分类（使用建造者模式）
 * @author: luhaoming
 * @date: 2018年9月11日 下午7:48:55
 */
public class NutritionFactsWithBuilder {
	private final int servingSize;  
	private final int servings;     
	private final int calories;       
	private final int fat;            
	private final int sodium;       
	private final int carbohydrate; 
	
	public static class Builder {
		// Required parameters必要参数
		private final int servingSize;
		private final int servings;
		
		// Optional parameters - initialized to default values
		// 可选参数（初始化默认值）
		private int calories = 0;
		private int fat = 0;
		private int carbohydrate = 0;
		private int sodium = 0;
		// 创建builder时初始化必要参数
		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}
		// 可选参数通过各自的方法设置
		public Builder calories(int val) {
			calories = val;
			return this;
		}
		
		public Builder fat(int val) {
			fat = val;
			return this;
		}
		
		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}
		
		public Builder sodium(int val) {
			sodium = val;
			return this;
		}
		
		public NutritionFactsWithBuilder build() {
			return new NutritionFactsWithBuilder(this);
		}
	}
	
	public NutritionFactsWithBuilder(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}

	@Override
	public String toString() {
		return "NutritionFactsWithBuilder [servingSize=" + servingSize + ", servings=" + servings + ", calories="
				+ calories + ", fat=" + fat + ", sodium=" + sodium + ", carbohydrate=" + carbohydrate + "]";
	}
}
