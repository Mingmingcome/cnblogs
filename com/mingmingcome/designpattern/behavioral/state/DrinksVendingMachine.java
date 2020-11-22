package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/21 21:44
 * @what 饮料自动售卖机
 */
public class DrinksVendingMachine {
    private DrinksVendingMachineState state;
    private NoCoinHasDrinks noCoinHasDrinks;
    private NoCoinNoDrinks noCoinNoDrinks;
    private HasCoinAndDrinks hasCoinAndDrinks;
    private HasCoinNoDrinks hasCoinNoDrinks;
    private int size = 0; // 当前饮料数
    private int capacity = 3; // 最大饮料数

    public DrinksVendingMachine() {
        this.noCoinHasDrinks = new NoCoinHasDrinks();
        this.noCoinNoDrinks = new NoCoinNoDrinks();
        this.hasCoinAndDrinks = new HasCoinAndDrinks();
        this.hasCoinNoDrinks = new HasCoinNoDrinks();
        this.state = noCoinNoDrinks;
    }

    public void insertCoin() {
        state.insertCoin(this);
    }

    public void pressButton() {
        state.pressButton(this);
    }

    public void addDrinks() {
        state.addDrinks(this, capacity - size); // 加满
    }

    public void changeState(DrinksVendingMachineState state) {
        this.state = state;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public NoCoinHasDrinks getNoCoinHasDrinks() {
        return noCoinHasDrinks;
    }

    public NoCoinNoDrinks getNoCoinNoDrinks() {
        return noCoinNoDrinks;
    }

    public HasCoinAndDrinks getHasCoinAndDrinks() {
        return hasCoinAndDrinks;
    }

    public HasCoinNoDrinks getHasCoinNoDrinks() {
        return hasCoinNoDrinks;
    }
}
