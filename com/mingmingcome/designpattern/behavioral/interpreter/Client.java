package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/4/22 8:11 PM
 * @who luhaoming
 * @what Client 客户端，负责调用解释器，解释器会解释出一个语法树，然后客户端再调用语法树的解释方法，解释出最终的结果。
 **/
public class Client {
    public static void main(String[] args) {
        String input = "ROT ORIGIN SCALE FOR T FROM 0 TO 360 STEP 1 DRAW T SEMICOLON";
        Parser parser = new Parser(input);
        Grammar grammar = new ROT();
        grammar.interpret(parser);
        System.out.println(parser.getOutput());
    }
}
