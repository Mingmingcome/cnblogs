package com.mingmingcome.designpattern.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @when 2023/4/17 10:56 PM
 * @who luhaoming
 * @what Token 用于表示代码中的每一个词法单元
 * Token类应该包含tokenType和tokenValue两个成员变量，分别表示词法单元的类型和值。
 **/
public class Token implements Grammar {

    public static final Map<String, Token> TOKEN_MAP;

    static {
        Map<String, Token> tokenMap = new HashMap<>();
        tokenMap.put("ROT", new Token(TokenType.ROT, "ROT"));
        tokenMap.put("IS", new Token(TokenType.IS, "IS"));
        tokenMap.put("ORIGIN", new Token(TokenType.ORIGIN, "ORIGIN"));
        tokenMap.put("SCALE", new Token(TokenType.SCALE, "SCALE"));
        tokenMap.put("FOR", new Token(TokenType.FOR, "FOR"));
        tokenMap.put("FROM", new Token(TokenType.FROM, "FROM"));
        tokenMap.put("TO", new Token(TokenType.TO, "TO"));
        tokenMap.put("STEP", new Token(TokenType.STEP, "STEP"));
        tokenMap.put("DRAW", new Token(TokenType.DRAW, "DRAW"));
        tokenMap.put("SEMICOLON", new Token(TokenType.SEMICOLON, ";"));
        tokenMap.put("LEFT_BRACKET", new Token(TokenType.LEFT_BRACKET, "("));
        tokenMap.put("RPAREN", new Token(TokenType.RPAREN, ")"));
        tokenMap.put("COMMA", new Token(TokenType.COMMA, ","));
        tokenMap.put("NUMBER", new Token(TokenType.NUMBER, null));
        tokenMap.put("RIGHT_BRACKET", new Token(TokenType.RIGHT_BRACKET, "]"));
        tokenMap.put("EOF", new Token(TokenType.EOF, null));
        tokenMap.put("T", new Token(TokenType.T, "T"));
        TOKEN_MAP = tokenMap;
    }
    private TokenType type;
    private Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public void interpret(Parser parser) {
        //
        parser.getLexer()
    }
}
