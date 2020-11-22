package com.mingmingcome.designpattern.behavioral.state;


/**
 * @who luhaoming
 * @when 2020/11/15 19:08
 * @what 已连接状态
 */
public class EstablishedState implements TCPState {
    @Override
    public void open(TCPConnectionUseStatePattern tcpConnectionUseStatePattern) {
        System.out.println("已连接，无需重复连接");
    }

    @Override
    public void close(TCPConnectionUseStatePattern tcpConnectionUseStatePattern) {
        System.out.println("关闭连接");
        System.out.println("更新状态");
        tcpConnectionUseStatePattern.changeState(tcpConnectionUseStatePattern.getClosedState());
    }
}
