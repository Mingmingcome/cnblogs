package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/21 21:23
 * @what 没有硬币有饮料
 */
public class NoCoinHasDrinks implements DrinksVendingMachineState {
    @Override
    public void insertCoin(DrinksVendingMachine drinksVendingMachine) {
        drinksVendingMachine.changeState(drinksVendingMachine.getHasCoinAndDrinks());
        System.out.println("投币成功，请按按钮！");
    }

    @Override
    public void pressButton(DrinksVendingMachine drinksVendingMachine) {
        System.out.println("请先投币，谢谢！");
    }

    @Override
    public void addDrinks(DrinksVendingMachine drinksVendingMachine, int num) {
        if (drinksVendingMachine.isFull()) {
            System.out.println("自动售卖机饮料已满，等待卖出后再添加！");
        }
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() + num);
        drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinHasDrinks());
        System.out.println("添加饮料成功！");
    }
}
