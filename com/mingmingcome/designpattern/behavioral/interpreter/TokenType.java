package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/4/17 10:28 PM
 * @who luhaoming
 * @what TokenType
 **/
public enum TokenType {
    ROT, IS, ORIGIN, SCALE, FOR, FROM, TO, STEP, DRAW, // 保留字
    T, // 参数
    SEMICOLON, COMMA, LEFT_BRACKET, RIGHT_BRACKET, // 分隔符
    EOF, // 文件结束符
    PLUS, MINUS, MUL, DIV, // 运算符
    LN, EXP, SQRT, SIN, COS, POWER, // 函数
    NUMBER // 数字
}
