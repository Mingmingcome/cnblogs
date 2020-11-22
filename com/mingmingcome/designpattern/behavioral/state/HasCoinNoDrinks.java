package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/21 21:25
 * @what 有硬币没有饮料
 */
public class HasCoinNoDrinks implements DrinksVendingMachineState {
    @Override
    public void insertCoin(DrinksVendingMachine drinksVendingMachine) {
        System.out.println("已有硬币，请取走你的硬币并按按钮，谢谢！");
    }

    @Override
    public void pressButton(DrinksVendingMachine drinksVendingMachine) {
        drinksVendingMachine.changeState(drinksVendingMachine.getNoCoinNoDrinks());
        System.out.println("暂时无饮料，请取走您的硬币，谢谢！");
    }

    @Override
    public void addDrinks(DrinksVendingMachine drinksVendingMachine, int num) {
        drinksVendingMachine.setSize(drinksVendingMachine.getSize() + num);
        drinksVendingMachine.changeState(drinksVendingMachine.getHasCoinAndDrinks());
        System.out.println("添加饮料成功！");
    }
}
