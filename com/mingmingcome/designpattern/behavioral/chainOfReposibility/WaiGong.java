package com.mingmingcome.designpattern.behavioral.chainOfReposibility;

/**
 * @who luhaoming
 * @when 2021/12/14 14:14
 * @what 外公
 */
public class WaiGong implements Support {
    private String name = "外公";
    private Support successor;
    @Override
    public void setSuccessor(Support successor) {
        this.successor = successor;
    }

    @Override
    public void handle(You you) {
        if (you.getObtainType() == ObtainType.NAME) {
            System.out.println(name + ":名叫晓月");
        } else if (you.getObtainType() == ObtainType.BORN) {
            System.out.println(name + ":你们多了一份责任");
        } else if (you.getObtainType() == ObtainType.EDUCATION) {
            System.out.println(name + ":暑假的大热天的扇子，冬天热乎的番薯");
        } else if (you.getObtainType() == ObtainType.WORK) {
            System.out.println(name + ":你很努力，也很棒");
        } else if (you.getObtainType() == ObtainType.MARRIAGE) {
            System.out.println(name + ":开心看到你结婚");
        }
    }
}
