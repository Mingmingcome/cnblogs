package com.mingmingcome.designpattern.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @when 2023/4/22 8:09 PM
 * @who luhaoming
 * @what Parser 解释器
 **/
public class Parser {
    // 语法规则
    private Lexer lexer;
    // 当前token
    private Token currentToken;
    // 输出
    private String output;
    // 旋转角度
    private double rot;
    // 原点坐标
    private double originX;
    private double originY;
    // 缩放比例
    private double scaleX;
    private double scaleY;
    private double from;
    private double to;
    private double step;
    private ExprNode xNode;
    private ExprNode yNode;

    // 语句解释映射
    private Map<TokenType, Grammar> statementMap;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.output = "";
        this.rot = 0;
        this.originX = 0;
        this.originY = 0;
        this.scaleX = 1;
        this.scaleY = 1;
        statementMap = new HashMap<>();
        statementMap.put(TokenType.ROT, new RotateStatement());
        statementMap.put(TokenType.ORIGIN, new OriginStatement());
        statementMap.put(TokenType.SCALE, new ScaleStatement());
        statementMap.put(TokenType.FOR, new ForStatement());
    }

    public void error() {
        throw new RuntimeException("Error parsing input");
    }

    // 匹配token
    public void matchToken(TokenType tokenType) {
        if (currentToken.getType() == tokenType) {
            System.out.println("match token type: " + currentToken.getType() + ",value:" +currentToken.getValue());
            currentToken = lexer.getNextToken();
        } else {
            error();
        }
    }

    // 解析
    public void parse() {
        currentToken = lexer.getNextToken();
        while (currentToken.getType() != TokenType.EOF) {
            if (currentToken.getType() == TokenType.ROT) {
                statementMap.get(TokenType.ROT).interpret(this);
            } else if (currentToken.getType() == TokenType.ORIGIN) {
                statementMap.get(TokenType.ORIGIN).interpret(this);
            } else if (currentToken.getType() == TokenType.SCALE) {
                statementMap.get(TokenType.SCALE).interpret(this);
            } else if (currentToken.getType() == TokenType.FOR) {
                statementMap.get(TokenType.FOR).interpret(this);
            } else {
                error();
            }
        }
    }


    // 处理表达式
    public ExprNode getExpression() {
        ExprNode left, right = null;
        left = getTerm();
        while (currentToken.getType() == TokenType.PLUS || currentToken.getType() == TokenType.MINUS) {
            TokenType type = currentToken.getType();
            matchToken(currentToken.getType());
            right = getTerm();
            left = new ExprNode(type, left, right, null, null);
        }
        return left;
    }

    // 处理项
    public ExprNode getTerm() {
        ExprNode left, right = null;
        left = getPower();
        while (currentToken.getType() == TokenType.MUL || currentToken.getType() == TokenType.DIV) {
            TokenType type = currentToken.getType();
            matchToken(currentToken.getType());
            right = getPower();
            left = new ExprNode(type, left, right, null, null);
        }
        return left;
    }

    public ExprNode getPower() {
        ExprNode left, right = null;
        left = getFactor();
        if (currentToken.getType() == TokenType.POWER) {
            TokenType type = currentToken.getType();
            matchToken(currentToken.getType());
            right = getFactor();
            left = new ExprNode(type, left, right, null, null);
        }
        return left;

    }


    // 处理因子
    public ExprNode getFactor() {
        ExprNode factor;
        if (currentToken.getType() == TokenType.PLUS) {
            //匹配一元加运算
            matchToken(TokenType.PLUS);
            //表达式退化为仅有右操作数的表达式
            return getExpression();
        } else if (currentToken.getType() == TokenType.MINUS) {
            //匹配一元减运算
            matchToken(TokenType.MINUS);
            //表达式转化为二元减运算的表达式
            ExprNode left = new ExprNode(TokenType.NUMBER, null, null, null, 0.0);
            factor = getExpression();
            return new ExprNode(TokenType.MINUS, left, factor, null, null);
        } else if (currentToken.getType() == TokenType.LEFT_BRACKET) {
            matchToken(TokenType.LEFT_BRACKET);
            factor = getExpression();
            matchToken(TokenType.RIGHT_BRACKET);
            return factor;
        } else if (currentToken.getType() == TokenType.NUMBER) {
            Token token = currentToken;
            matchToken(TokenType.NUMBER);
            return new ExprNode(TokenType.NUMBER, null, null, null, (Double) token.getValue());
        } else if (currentToken.getType() == TokenType.EXP) {
            matchToken(TokenType.EXP);
            matchToken(TokenType.LEFT_BRACKET);
            factor = getExpression();
            matchToken(TokenType.RIGHT_BRACKET);
            return new ExprNode(TokenType.EXP, null, null, factor, null);
        } else if (currentToken.getType() == TokenType.SQRT) {
            matchToken(TokenType.SQRT);
            matchToken(TokenType.LEFT_BRACKET);
            factor = getExpression();
            matchToken(TokenType.RIGHT_BRACKET);
            return new ExprNode(TokenType.SQRT, null, null, factor, null);
        } else if (currentToken.getType() == TokenType.LN) {
            matchToken(TokenType.LN);
            matchToken(TokenType.LEFT_BRACKET);
            factor = getExpression();
            matchToken(TokenType.RIGHT_BRACKET);
            return new ExprNode(TokenType.LN, null, null, factor, null);
        } else if (currentToken.getType() == TokenType.T)  {
            matchToken(TokenType.T);
            return new ExprNode(TokenType.T, null, null, null, null);
        } else if (currentToken.getType() == TokenType.SIN) {
            matchToken(TokenType.SIN);
            matchToken(TokenType.LEFT_BRACKET);
            factor = getExpression();
            matchToken(TokenType.RIGHT_BRACKET);
            return new ExprNode(TokenType.SIN, null, null, factor, null);
        } else if (currentToken.getType() == TokenType.COS) {
            matchToken(TokenType.COS);
            matchToken(TokenType.LEFT_BRACKET);
            factor = getExpression();
            matchToken(TokenType.RIGHT_BRACKET);
            return new ExprNode(TokenType.COS, null, null, factor, null);
        } else {
            throw new RuntimeException("Invalid syntax");
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

    public Double getRot() {
        return rot;
    }

    public void setRot(double rot) {
        this.rot = rot;
    }

    public Double getOriginX() {
        return originX;
    }

    public void setOriginX(double originX) {
        this.originX = originX;
    }

    public Double getOriginY() {
        return originY;
    }

    public void setOriginY(double originY) {
        this.originY = originY;
    }

    public Double getScaleX() {
        return scaleX;
    }

    public void setScaleX(Double scaleX) {
        this.scaleX = scaleX;
    }

    public Double getScaleY() {
        return scaleY;
    }

    public void setScaleY(Double scaleY) {
        this.scaleY = scaleY;
    }

    public Lexer getLexer() {
        return lexer;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public Double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

}
