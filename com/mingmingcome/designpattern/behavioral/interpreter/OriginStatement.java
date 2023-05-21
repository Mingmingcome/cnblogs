package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/4/30 7:10 PM
 * @who luhaoming
 * @what OriginStatement 原点语句
 **/
public class OriginStatement implements Grammar {
    @Override
    public void interpret(Parser parser) {
        /*
         * 解释ORIGIN IS (NUMBER, NUMBER)，即原点坐标，将坐标存入上下文中，供后续语句使用。
         * 语法分析：先识别ORIGIN，然后再识别IS，最后识别左括号。
         * 识别左括号后，识别NUMBER，识别逗号，识别NUMBER，识别右括号。
         */
        parser.matchToken(TokenType.ORIGIN);
        parser.matchToken(TokenType.IS);
        parser.matchToken(TokenType.LEFT_BRACKET);
        parser.setOriginX((Double) parser.getCurrentToken().getValue());
        parser.matchToken(TokenType.NUMBER);
        parser.matchToken(TokenType.COMMA);
        parser.setOriginY((Double) parser.getCurrentToken().getValue());
        parser.matchToken(TokenType.NUMBER);
        parser.matchToken(TokenType.RIGHT_BRACKET);
        parser.matchToken(TokenType.SEMICOLON);
    }
}
