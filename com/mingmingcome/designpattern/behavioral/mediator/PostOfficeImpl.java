package com.mingmingcome.designpattern.behavioral.mediator;

import java.util.HashMap;

/**
 * @ClassName PostOfficeImpl
 * @Description 具体中介者
 * @Author luhaoming
 * @Date 2019/11/16 1:25
 */
public class PostOfficeImpl implements PostOffice {
    /**
     * 收信人信息
     */
    private HashMap villagerMap = new HashMap<String, Villager>();

    @Override
    public void addPeople(Villager villager) {
        villagerMap.put(villager.getClass().getSimpleName(), villager);
    }

    @Override
    public void deliverLetters(String letters, String receiver) {
        System.out.println("=>收信：邮局收到要寄的信");
        Villager villager = (Villager) villagerMap.get(receiver);
        System.out.println("=>送信：拿出地址本查询收信人地址是：" + villager.getAddress() + "，送信");
        System.out.println("=>收信人看信：");
        villager.receiveLetter(letters);
    }
}
