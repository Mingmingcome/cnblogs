package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/21 21:23
 * @what 没有硬币没有饮料
 */
public class NoCoinNoDrinks implements DrinksVendingMachineState {
    @Override
    public void insertCoin(DrinksVendingMachine drinksVendingMachine) {
        drinksVendingMachine.changeState(drinksVendingMachine.getHasCoinNoDrinks());
        System.out.println("投币成功，请按按钮!");
    }

    @Override
    public void pressButton(DrinksVendingMachine drinksVendingMachine) {
        System.out.println("饮料已空，请联系工作人员添加饮料！");
    }

    @Override
    public void addDrinks(DrinksVendingMachine drinksVendingMachine, int num) {
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() + num);
        drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinHasDrinks());
        System.out.println("添加饮料成功！");
    }
}
