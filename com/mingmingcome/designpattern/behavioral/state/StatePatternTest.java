package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/15 18:20
 * @what 状态模式测试
 */
public class StatePatternTest {
    public static void main(String[] args) {
        // 1.初始化状态：NoCoinNoDrinks
        DrinksVendingMachine drinksVendingMachine = new DrinksVendingMachine();
        // 无效行为，不会改变状态
        drinksVendingMachine.pressButton();
        // 2.状态：NoCoinNoDrinks -> HasCoinNoDrinks
        drinksVendingMachine.insertCoin();
        drinksVendingMachine.insertCoin();
        // 3.状态：HasCoinNoDrinks -> NoCoinNoDrinks
        drinksVendingMachine.pressButton();
        // 4.状态：NoCoinNoDrinks -> NoCoinHasDrinks
        drinksVendingMachine.addDrinks();
        // 5、状态：(NoCoinHasDrinks <-> HasCoinAndDrinks){多次} -> NoCoinNoDrinks
        while (drinksVendingMachine.getSize() > 0) {
            drinksVendingMachine.insertCoin();
            drinksVendingMachine.insertCoin();
            drinksVendingMachine.pressButton();
            drinksVendingMachine.pressButton();
        }
    }
}
