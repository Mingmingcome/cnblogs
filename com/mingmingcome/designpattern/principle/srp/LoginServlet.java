package com.mingmingcome.designpattern.principle.srp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取前端传过来的数据
        // 2、连接数据库，查询数据
        // 3、比较数据，得出结果
        // 4、封装结果，返回给前端
    }
}
