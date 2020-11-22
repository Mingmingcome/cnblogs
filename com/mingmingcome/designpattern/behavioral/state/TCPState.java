package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/15 18:54
 * @what TCPState
 */
public interface TCPState {
    void open(TCPConnectionUseStatePattern tcpConnectionUseStatePattern);
    void close(TCPConnectionUseStatePattern tcpConnectionUseStatePattern);
    // ...（省略）
}
