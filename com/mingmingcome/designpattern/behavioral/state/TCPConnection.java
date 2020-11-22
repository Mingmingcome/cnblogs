package com.mingmingcome.designpattern.behavioral.state;

/**
 * @who luhaoming
 * @when 2020/11/15 18:32
 * @what TCP连接
 */
public class TCPConnection {
    private String state;

    void open() {
        switch (state) {
            case "closed":
                // open创建连接
                System.out.println("open创建连接");
                state = "established";
                break;
            case "established":
            case "listening":
                // 无效行为
                break;
        }

    }

    void close() {
        switch (state) {
            case "closed":
                // 无效行为
                break;
            case "established":
            case "listening":
                // close关闭连接
                System.out.println("close关闭连接");
                break;
        }
    }

    // ...（省略）

}
