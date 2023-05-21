package com.mingmingcome.designpattern.behavioral.interpreter;

/**
 * @when 2023/5/18 7:21 PM
 * @who luhaoming
 * @what ExprNode 表达式节点
 **/
public class ExprNode {
    // 语法树的节点类型
    private TokenType tokenType;
    // 语法树的左右子节点（+、-、*、/）
    private ExprNode left;
    private ExprNode right;
    // 语法树的单叶子节点（ln、exp、sqrt、-）
    private ExprNode child;
    // 语法树的叶子节点（数字）
    private Double constantValue;


    public ExprNode(TokenType tokenType, ExprNode left, ExprNode right, ExprNode child, Double constantValue) {
        this.tokenType = tokenType;
        this.left = left;
        this.right = right;
        this.child = child;
        this.constantValue = constantValue;
    }

    public ExprNode() {
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public ExprNode getLeft() {
        return left;
    }

    public void setLeft(ExprNode left) {
        this.left = left;
    }

    public ExprNode getRight() {
        return right;
    }

    public void setRight(ExprNode right) {
        this.right = right;
    }

    public ExprNode getChild() {
        return child;
    }

    public void setChild(ExprNode child) {
        this.child = child;
    }

    public double getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(Double constantValue) {
        this.constantValue = constantValue;
    }
}
