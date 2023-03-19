package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 14:28
 * @what 家
 */
public class Family {
    public static void main(String[] args) {
        // 爸爸
        BaBa baBa = new BaBa();
        // 妈妈
        MaMa maMa = new MaMa();
        // 外公
        WaiGong waiGong = new WaiGong();

        // 结婚，爸爸成了妈妈的支持
        maMa.setSuccessor(baBa);
        // 外公对爸爸说，我们是一家人，我可以成为你的支持
        baBa.setSuccessor(waiGong);
        // 妈妈怀孕了
        You you = new You();
        you.setAge(-1);
        // 你一生的支持，103岁die
        while(you.getAge() < 103) {
            // 你主观或者客观提出了请求
            produceRequest(you);
            // 家人支持处理你的请求
            handleRequest(you, maMa);
            // 下一重要阶段
            setNextAge(you);
        }

    }

    private static void setNextAge(You you) {
        if (you.getAge() == -1) {
            you.setAge(0);
        } else if (you.getAge() == 0) {
            you.setAge(3);
        } else if (you.getAge() >= 3 && you.getAge() < 22) {
            you.setAge(22);
        } else if (you.getAge() >= 22 && you.getAge() < 24) {
            you.setAge(24);
        } else if (you.getAge() == 24) {
            you.setAge(103);
        }
    }

    private static void handleRequest(You you, MaMa maMa) {
        maMa.handle(you);
    }

    private static void produceRequest(You you) {
        if (you.getAge() == -1) {
            you.setObtainType(ObtainType.NAME);
        } else if (you.getAge() == 0) {
            you.setObtainType(ObtainType.BORN);
        } else if (you.getAge() >= 3 && you.getAge() < 22) {
            you.setObtainType(ObtainType.EDUCATION);
        } else if (you.getAge() >= 22 && you.getAge() < 24) {
            you.setObtainType(ObtainType.WORK);
        } else if (you.getAge() == 24) {
            you.setObtainType(ObtainType.MARRIAGE);
        }
    }
}
