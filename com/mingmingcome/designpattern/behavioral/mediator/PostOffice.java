package com.mingmingcome.designpattern.behavioral.mediator;

/**
 * @ClassName PostOffice
 * @Description 抽象中介者
 * @Author luhaoming
 * @Date 2019/11/16 1:06
 */
public interface PostOffice {
    /**
     * 送信
     * @param letters 信
     * @param receiver 收信人
     */
    void deliverLetters(String letters, String receiver);

    /**
     * 添加收信人
     * @param villager
     */
    void addPeople(Villager villager);
}
