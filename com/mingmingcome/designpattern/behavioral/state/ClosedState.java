package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/15 19:08
 * @what 已关闭状态
 */
public class ClosedState implements TCPState {
    @Override
    public void open(TCPConnectionUseStatePattern tcpConnectionUseStatePattern) {
        System.out.println("创建连接");
        System.out.println("更新状态");
        tcpConnectionUseStatePattern.changeState(tcpConnectionUseStatePattern.getEstablishedState());
    }

    @Override
    public void close(TCPConnectionUseStatePattern tcpConnectionUseStatePattern) {
        System.out.println("已关闭，无需重复关闭");
    }
}
