package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/21 21:27
 * @what 饮料自动售卖机
 */
public interface DrinksVendingMachineState {
    void insertCoin(DrinksVendingMachine drinksVendingMachine);
    void pressButton(DrinksVendingMachine drinksVendingMachine);
    void addDrinks(DrinksVendingMachine drinksVendingMachine, int num);
}
