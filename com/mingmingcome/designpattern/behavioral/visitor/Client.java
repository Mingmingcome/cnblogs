package com.mingmingcome.designpattern.behavioral.visitor;

/**
 * @who luhaoming
 * @when 2023/4/5
 * @what Client 客户端
 */
public class Client {
    public static void main(String[] args) {
        Optimist optimist = new Optimist();
        Pessimist pessimist = new Pessimist();

        Life life = new Life();
        System.out.println("乐观者：");
        life.visitTime(optimist);
        System.out.println("悲观者：");
        life.visitTime(pessimist);
    }
}
