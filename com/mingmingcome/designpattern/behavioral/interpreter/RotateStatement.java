package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/4/22 8:15 PM
 * @who luhaoming
 * @what RotateStatement 旋转语句
 **/
public class RotateStatement implements Grammar {
    @Override
    public void interpret(Parser parser) {
        /**
         * 解释ROT IS NUMBER，即旋转角度，将角度存入上下文中，供后续语句使用。
         * 语法分析：先识别ROT，然后再识别IS，最后识别NUMBER。
         **/
        parser.matchToken(TokenType.ROT);
        parser.matchToken(TokenType.IS);
        parser.setRot((Double) parser.getCurrentToken().getValue());
        parser.matchToken(TokenType.NUMBER);
        parser.matchToken(TokenType.SEMICOLON);
    }
}
