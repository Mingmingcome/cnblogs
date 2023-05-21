package com.mingmingcome.designpattern.behavioral.interpreter;

import java.io.IOException;

/**
 * @when 2023/4/22 8:11 PM
 * @who luhaoming
 * @what Client 客户端，负责调用解释器，解释器会解释出一个语法树，然后客户端再调用语法树的解释方法，解释出最终的结果。
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        Semantic semantic = new Semantic();
        semantic.analyze();
    }
}
