package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/4/22 8:09 PM
 * @who luhaoming
 * @what Parser 解释器
 **/
public class Parser {
    // Parser类应该包含一个指向Lexer对象的指针，并提供一个parse()方法，该方法从Lexer对象中获取下一个词法单元，并根据词法单元的类型执行相应的操作。在执行操作时，需要维护一个全局的状态，包括ROT旋转角度、ORIGIN坐标原点、SCALE横纵坐标比例因子等信息。
    // 为了简化解释器的实现，我们将ROT、ORIGIN、SCALE等信息都存储在Parser类中，而不是单独创建一个类来存储这些信息。

    private Lexer lexer;
    private Token currentToken;
    private String output;
    private int rot;
    private int originX;
    private int originY;
    private int scaleX;
    private int scaleY;

    public Parser(String input) {
        lexer = new Lexer(input);
        output = "";
        rot = 0;
        originX = 0;
        originY = 0;
        scaleX = 1;
        scaleY = 1;
    }

    public void error() {
        throw new RuntimeException("Error parsing input");
    }

    public void matchToken(TokenType tokenType) {
        if (currentToken.getType() == tokenType) {
            currentToken = lexer.getNextToken();
        } else {
            error();
        }
    }

    public void parse() {
        currentToken = lexer.getNextToken();
        while (currentToken.getType() != TokenType.EOF) {
            if (currentToken.getType() == TokenType.ROT) {
                matchToken(TokenType.ROT);
                rot = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
            } else if (currentToken.getType() == TokenType.ORIGIN) {
                matchToken(TokenType.ORIGIN);
                matchToken(TokenType.IS);
                matchToken(TokenType.LEFT_BRACKET);
                originX = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.COMMA);
                originY = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.RIGHT_BRACKET);
            } else if (currentToken.getType() == TokenType.SCALE) {
                matchToken(TokenType.SCALE);
                matchToken(TokenType.IS);
                matchToken(TokenType.LEFT_BRACKET);
                scaleX = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.COMMA);
                scaleY = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.RIGHT_BRACKET);
            } else if (currentToken.getType() == TokenType.FOR) {
                matchToken(TokenType.FOR);
                int t = (Integer) currentToken.getValue();
                matchToken(TokenType.T);
                matchToken(TokenType.FROM);
                int from = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.TO);
                int to = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.STEP);
                int step = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.DRAW);
                matchToken(TokenType.LEFT_BRACKET);
                int x = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.COMMA);
                int y = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.RIGHT_BRACKET);
                for (int i = from; i <= to; i += step) {
                    output += "ROT " + rot + " ORIGIN " + originX + " " + originY + " SCALE " + scaleX + " " + scaleY + " DRAW " + (x * i) + " " + (y * i
                    ) + "\r";
                }
            } else if (currentToken.getType() == TokenType.DRAW) {
                matchToken(TokenType.DRAW);
                matchToken(TokenType.LEFT_BRACKET);
                int x = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.COMMA);
                int y = (Integer) currentToken.getValue();
                matchToken(TokenType.NUMBER);
                matchToken(TokenType.RIGHT_BRACKET);
                output += "ROT " + rot + " ORIGIN " + originX + " " + originY + " SCALE " + scaleX + " " + scaleY + " DRAW " + x + " " + y + "\r";
            } else {
                error();
            }
        }
    }

    public String getOutput() {
        return output;
    }

    public Token getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Token currentToken) {
        this.currentToken = currentToken;
    }

    public int getRot() {
        return rot;
    }

    public void setRot(int rot) {
        this.rot = rot;
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public int getScaleX() {
        return scaleX;
    }

    public void setScaleX(int scaleX) {
        this.scaleX = scaleX;
    }

    public int getScaleY() {
        return scaleY;
    }

    public void setScaleY(int scaleY) {
        this.scaleY = scaleY;
    }

    public Lexer getLexer() {
        return lexer;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }
}
