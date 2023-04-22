package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/4/22 8:25 PM
 * @who luhaoming
 * @what Lexer 用于将代码中的字符流转换成一个个词法单元
 **/
public class Lexer {
    // Lexer类应该包含一个指向代码字符串的指针，并且提供一个getNextToken()方法，该方法每次读取一个词法单元并将指针指向下一个词法单元。
    // 在getNextToken()方法中，需要识别出各种不同类型的词法单元，并返回对应的Token对象。
    // 词法单元的类型包括ROT、IS、NUMBER、ORIGIN、SCALE、ROTATE、FOR、FROM、TO、STEP、DRAW、TO、SEMICOLON、LEFT_BRACKET、RIGHT_BRACKET、COMMA等。
    // 词法单元的类型可以使用枚举类型TokenType来表示。

    private String input;
    private int pos;
    private char currentChar;
    private StringBuffer charBuffer = new StringBuffer();

    public Lexer(String input) {
        this.input = input;
        pos = 0;
        currentChar = input.charAt(pos);
    }

    public void error() {
        throw new RuntimeException("Error parsing input");
    }

    public void advance() {
        pos++;
        if (pos > input.length() - 1) {
            currentChar = 0;
        } else {
            currentChar = input.charAt(pos);
        }
    }

    public void skipWhitespace() {
        while (currentChar != 0 && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    public int integer() {
        StringBuilder result = new StringBuilder();
        while (currentChar != 0 && Character.isDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }
        return Integer.parseInt(result.toString());
    }

    public Token getNextToken() {
        // 识别ROT、IS、ORIGIN、SCALE、FOR、FROM、TO、STEP、DRAW、SEMICOLON、LEFT_BRACKET、RIGHT_BRACKET、COMMA等
        while (currentChar != 0) {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }
            if (Character.isDigit(currentChar)) {
                return new Token(TokenType.NUMBER, integer());
            }
            charBuffer.append(currentChar);
            advance();
            if (currentChar == ' ') {
                String token = charBuffer.toString();
                charBuffer = new StringBuffer();
                switch (token) {
                    case "ROT":
                        return new Token(TokenType.ROT, token);
                    case "IS":
                        return new Token(TokenType.IS, token);
                    case "ORIGIN":
                        return new Token(TokenType.ORIGIN, token);
                    case "SCALE":
                        return new Token(TokenType.SCALE, token);
                    case "FOR":
                        return new Token(TokenType.FOR, token);
                    case "FROM":
                        return new Token(TokenType.FROM, token);
                    case "TO":
                        return new Token(TokenType.TO, token);
                    case "STEP":
                        return new Token(TokenType.STEP, token);
                    case "DRAW":
                        return new Token(TokenType.DRAW, token);
                    case "SEMICOLON":
                        return new Token(TokenType.SEMICOLON, token);
                    case "LEFT_BRACKET":
                        return new Token(TokenType.LEFT_BRACKET, token);
                    case "RIGHT_BRACKET":
                        return new Token(TokenType.RIGHT_BRACKET, token);
                    case "COMMA":
                        return new Token(TokenType.COMMA, token);
                    default:
                        error();
                }
            }
        }
        return new Token(TokenType.EOF, null);

    }
}
