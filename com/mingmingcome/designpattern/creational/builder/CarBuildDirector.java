package com.mingmingcome.designpattern.creational.builder;

/** 
 * @className: CarBuildDirector
 * @Description: TODO
 * @author: luhaoming
 * @date: 2018年9月11日 下午12:31:01
 */
// 指挥者和客户端
public class CarBuildDirector {
	private CarBuilder builder;
	
	public CarBuildDirector(CarBuilder builder) {
		this.builder = builder;
	}
	
	public Car construct() {
		return builder.setWheels(4)
				.setColor("Red")
				.build();
	}
	public static void main(String[] args) {
		CarBuilder builder = new CarBuilderImpl();
		
		CarBuildDirector carBuildDirector = new CarBuildDirector(builder);
		
		Car car = carBuildDirector.construct();
		
		System.out.println(car);
	}
}
// 产品
class Car {
	private int wheels;
	private String color;
	
	public Car() {}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [wheels=" + wheels + ", color=" + color + "]";
	}
}
// 抽象建造者
interface CarBuilder {
	Car build();
	
	CarBuilder setWheels(int wheels);
	
	CarBuilder setColor(String color);
}
// 具体建造者
class CarBuilderImpl implements CarBuilder{
	private Car car;
	
	public CarBuilderImpl() {
		this.car = new Car();
	}

	@Override
	public Car build() {
		return car;
	}

	@Override
	public CarBuilder setWheels(int wheels) {
		car.setWheels(wheels);
		// 注意这里返回的是this对象，就是对象本身
		// 这样的话就可以在Director使用类似链式调用连续调用这个对象的其他方法
		return this;
	}

	@Override
	public CarBuilder setColor(String color) {
		car.setColor(color);
		return this;
	}
}

