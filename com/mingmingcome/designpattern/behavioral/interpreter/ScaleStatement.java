package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/4/30 7:37 PM
 * @who luhaoming
 * @what ScaleStatement 比例语句
 **/
public class ScaleStatement implements Grammar {
    @Override
    public void interpret(Parser parser) {
        /**
         * 解释SCALE IS (NUMBER, NUMBER)，即比例因子，将比例因子存入上下文中，供后续语句使用。
         * 语法分析：先识别SCALE，然后再识别IS，最后识别左括号。
         * 识别左括号后，识别NUMBER，识别逗号，识别NUMBER，识别右括号。
         */
        parser.matchToken(TokenType.SCALE);
        parser.matchToken(TokenType.IS);
        parser.matchToken(TokenType.LEFT_BRACKET);
        parser.setScaleX((Double) parser.getCurrentToken().getValue());
        parser.matchToken(TokenType.NUMBER);
        parser.matchToken(TokenType.COMMA);
        parser.setScaleY((Double) parser.getCurrentToken().getValue());
        parser.matchToken(TokenType.NUMBER);
        parser.matchToken(TokenType.RIGHT_BRACKET);
        parser.matchToken(TokenType.SEMICOLON);
    }
}
