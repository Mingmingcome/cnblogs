package com.mingmingcome.designpattern.behavioral.interpreter;

import javafx.scene.chart.XYChart;

/**
 * @when 2023/4/30 7:13 PM
 * @who luhaoming
 * @what ForStatement 循环语句
 **/
public class ForStatement implements Grammar {
    @Override
    public void interpret(Parser parser) {
        // 解释FOR T FROM NUMBER TO NUMBER STEP NUMBER DRAW (NUMBER, NUMBER)，即循环语句
        // 语法分析：先识别FOR，然后再识别T，最后识别FROM。
        parser.matchToken(TokenType.FOR);
        parser.matchToken(TokenType.T);
        parser.matchToken(TokenType.FROM);
        parser.setFrom((Double) parser.getCurrentToken().getValue());
        parser.matchToken(TokenType.NUMBER);
        parser.matchToken(TokenType.TO);
        parser.setTo((Double) parser.getCurrentToken().getValue());
        parser.matchToken(TokenType.NUMBER);
        parser.matchToken(TokenType.STEP);
        parser.setStep((Double) parser.getCurrentToken().getValue());
        parser.matchToken(TokenType.NUMBER);
        parser.matchToken(TokenType.DRAW);
        parser.matchToken(TokenType.LEFT_BRACKET);
        ExprNode xNode = parser.getExpression();
        parser.matchToken(TokenType.COMMA);
        ExprNode yNode = parser.getExpression();
        parser.matchToken(TokenType.RIGHT_BRACKET);
        parser.matchToken(TokenType.SEMICOLON);

        XYChart.Series<Double, Double> series = DrawUtils.newSeries();

        for (double parameter = parser.getFrom(); parameter <= parser.getTo(); parameter += parser.getStep()) {
            draw(xNode, yNode, parameter, parser, series);
        }
    }

    private void draw(ExprNode xNode, ExprNode yNode, double parameter, Parser parser,
                      XYChart.Series<Double, Double> series) {
        // 计算表达式的值，得到点的原始坐标
        double x = getExprValue(xNode, parameter);
        double y = getExprValue(yNode, parameter);
        // 进行比例变换
        x *= parser.getScaleX();
        y *= parser.getScaleY();
        // 进行旋转变换
        double x1 = x * Math.cos(parser.getRot()) - y * Math.sin(parser.getRot());
        double y1 = x * Math.sin(parser.getRot()) + y * Math.cos(parser.getRot());
        x = x1;
        y = y1;
        // 进行平移变换
        x += parser.getOriginX();
        y += parser.getOriginY();

        DrawUtils.addXYChartData(series, x, y);

        System.out.println("x = " + x + ", y = " + y);
    }

    private double getExprValue(ExprNode exprNode, double i) {
        if (exprNode.getTokenType() == TokenType.NUMBER) {
            return exprNode.getConstantValue();
        } else if (exprNode.getTokenType() == TokenType.T) {
            return i;
        } else if (exprNode.getTokenType() == TokenType.PLUS) {
            return getExprValue(exprNode.getLeft(), i) + getExprValue(exprNode.getRight(), i);
        } else if (exprNode.getTokenType() == TokenType.MINUS) {
            return getExprValue(exprNode.getLeft(), i) - getExprValue(exprNode.getRight(), i);
        } else if (exprNode.getTokenType() == TokenType.MUL) {
            return getExprValue(exprNode.getLeft(), i) * getExprValue(exprNode.getRight(), i);
        } else if (exprNode.getTokenType() == TokenType.DIV) {
            return getExprValue(exprNode.getLeft(), i) / getExprValue(exprNode.getRight(), i);
        } else if (exprNode.getTokenType() == TokenType.LN) {
            return Math.log(getExprValue(exprNode.getChild(), i));
        } else if (exprNode.getTokenType() == TokenType.EXP) {
            return Math.exp(getExprValue(exprNode.getChild(), i));
        } else if (exprNode.getTokenType() == TokenType.SQRT) {
            return Math.sqrt(getExprValue(exprNode.getChild(), i));
        } else if (exprNode.getTokenType() == TokenType.SIN) {
            return Math.sin(getExprValue(exprNode.getChild(), i));
        } else if (exprNode.getTokenType() == TokenType.COS) {
            return Math.cos(getExprValue(exprNode.getChild(), i));
        } else if (exprNode.getTokenType() == TokenType.POWER) {
            return Math.pow(getExprValue(exprNode.getLeft(), i), getExprValue(exprNode.getRight(), i));
        } else {
            throw new RuntimeException("不支持的运算符");
        }
    }

}
