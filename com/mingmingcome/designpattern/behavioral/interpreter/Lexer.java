package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/4/22 8:25 PM
 * @who luhaoming
 * @what Lexer 用于将代码中的字符流转换成一个个词法单元
 **/
public class Lexer {
    private String input;
    private int pos;
    private char currentChar;
    private StringBuffer charBuffer = new StringBuffer();

    public Lexer(String input) {
        this.input = input.toUpperCase();
        pos = 0;
        currentChar = this.input.charAt(pos);
    }

    public Token getNextToken() {
        while (true) {
            charBuffer.delete(0, charBuffer.length());
            // 如果当前字符是字母，返回一个标识符类型的token
            if (Character.isAlphabetic(currentChar)) {
                while (true) {
                    charBuffer.append(currentChar);
                    advance();
                    if (!Character.isAlphabetic(currentChar)) {
                        return Token.TOKEN_MAP.get(charBuffer.toString());
                    }
                }
            }
            // 如果当前字符是空白字符，跳过
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }
            // 如果当前字符是数字，返回一个数字类型的token
            if (Character.isDigit(currentChar)) {
                return Token.TOKEN_MAP.get("NUMBER").setValue(number());
            }
            // 如果当前字符是左括号，返回一个左括号类型的token
            if (currentChar == '(') {
                advance();
                return Token.TOKEN_MAP.get("(");
            }
            // 如果当前字符是右括号，返回一个右括号类型的token
            if (currentChar == ')') {
                advance();
                return Token.TOKEN_MAP.get(")");
            }
            // 如果当前字符是逗号，返回一个逗号类型的token
            if (currentChar == ',') {
                advance();
                return Token.TOKEN_MAP.get(",");
            }
            // 如果当前字符是分号，返回一个分号类型的token
            if (currentChar == ';') {
                advance();
                return Token.TOKEN_MAP.get(";");
            }
            // 如果当前字符是+，返回一个加号类型的token
            if (currentChar == '+') {
                advance();
                return Token.TOKEN_MAP.get("+");
            }
            // 如果当前字符是-，返回一个减号类型的token
            if (currentChar == '-') {
                advance();
                return Token.TOKEN_MAP.get("-");
            }
            // 如果当前字符是*，返回一个乘号类型的token
            if (currentChar == '*') {
                advance();
                return Token.TOKEN_MAP.get("*");
            }
            // 如果当前字符是/，返回一个除号类型的token
            if (currentChar == '/') {
                advance();
                return Token.TOKEN_MAP.get("/");
            }
            // 如果当前字符是^，返回一个乘方类型的token
            if (currentChar == '^') {
                advance();
                return Token.TOKEN_MAP.get("^");
            }
            // 如果当前字符是文件最后一个字符，返回一个EOF(Ending of File)类型的token
            if (currentChar == 0) {
                return Token.TOKEN_MAP.get("EOF");
            }
            // 如果当前字符是空格，则将charBuffer中的字符转换成token
            if (currentChar == ' ') {
                String token = charBuffer.toString();
                charBuffer = new StringBuffer();
                return Token.TOKEN_MAP.get(token);
            }
            // 如果当前字符是字母，则将当前字符加入到charBuffer中
            charBuffer.append(currentChar);
            advance();
        }
    }

    public void error() {
        throw new RuntimeException("Error parsing input");
    }

    public void advance() {
        pos++;
        if (pos > input.length() - 1) {
            // 表示已经到了输入的结尾
            currentChar = 0;
        } else {
            // 获取下一个字符
            currentChar = input.charAt(pos);
        }
    }

    // 跳过空白字符
    public void skipWhitespace() {
        while (currentChar != 0 && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    public double number() {
        while (currentChar != 0 && (Character.isDigit(currentChar) || currentChar == '.')) {
            charBuffer.append(currentChar);
            advance();
        }
        return Double.parseDouble(charBuffer.toString());
    }
}
