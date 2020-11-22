package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/15 18:32
 * @what TCP连接
 */
public class TCPConnectionUseStatePattern {
    private TCPState tcpState;
    private ClosedState closedState;
    private EstablishedState establishedState;

    TCPConnectionUseStatePattern() {
        closedState = new ClosedState();
        establishedState = new EstablishedState();
        tcpState = closedState;
    }

    void open() {
        tcpState.open(this);
    }

    void close() {
        tcpState.close(this);
    }

    void changeState(TCPState tcpState) {
        this.tcpState = tcpState;
    }

    public ClosedState getClosedState() {
        return closedState;
    }

    public EstablishedState getEstablishedState() {
        return establishedState;
    }
}
