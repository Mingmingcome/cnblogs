package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/21 21:24
 * @what 有硬币有饮料
 */
public class HasCoinAndDrinks implements DrinksVendingMachineState {
    @Override
    public void insertCoin(DrinksVendingMachine drinksVendingMachine) {
        System.out.println("已有硬币，请取走你的硬币并按按钮，谢谢！");
    }

    @Override
    public void pressButton(DrinksVendingMachine drinksVendingMachine) {
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() - 1);
        if (drinksVendingMachine.isEmpty()) {
            drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinNoDrinks());
        } else {
            drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinHasDrinks());
        }
        System.out.println("请取走您的饮料，谢谢！");
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
