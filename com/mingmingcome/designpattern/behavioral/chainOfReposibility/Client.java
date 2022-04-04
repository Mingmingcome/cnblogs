package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 14:28
 * @what 客户端
 */
public class Client {
    public static void main(String[] args) {
        // 爸爸
        BaBa baBa = new BaBa();
        // 妈妈
        MaMa maMa = new MaMa();
        // 结婚，爸爸成了妈妈的支持
        maMa.setSuccessor(baBa);
        // 外公对爸爸说，我们是一家人，我可以成为你的支持
        WaiGong waiGong = new WaiGong();
        baBa.setSuccessor(waiGong);
        // 妈妈怀孕了
        You you = new You();
        // 你一生的支持
        while(true) {
            // 你主观或者客观提出了请求
            produceRequest(you);
            // 家人支持处理你的请求
            handleRequest(you);
            // 岁数加一
            you.incrAge();
            baBa.incrAge();
            maMa.incrAge();
            waiGong.incrAge();
        }

    }

    private static void handleRequest(You you) {

    }

    private static void produceRequest(You you) {
        if (you.getAge() == -1) {
            you.setPhase("name");
        } else if (you.getAge() == 0) {
            you.setPhase("sex");
        } else if (you.getAge() == 3) {
            you.setPhase("幼儿园");
        } else if (you.getAge() == 7) {
            you.setPhase("小学");
        } else if (you.getAge() == 12) {
            you.setPhase("初中");
        } else if (you.getAge() == 15) {
            you.setPhase("高中");
        } else if (you.getAge() == 18) {
            you.setPhase("大学");
        } else if (you.getAge() == 21) {
            you.setPhase("工作");
        } else if (you.getAge() == 25) {
            you.setPhase("买车");
        } else if (you.getAge() == 28) {
            you.setPhase("买房");
        } else if (you.getAge() == 29) {
            you.setPhase("结婚");
        } else if (you.getAge() == 30) {
            you.setPhase("生娃");
        }
    }
}
