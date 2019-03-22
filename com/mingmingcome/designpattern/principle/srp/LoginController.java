package com.mingmingcome.designpattern.principle.srp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {

    private LoginService loginService;

    public ModelAndView Login(HttpServletRequest req, HttpServletResponse resp) {
        // 1、获取前端传过来的数据
        loginService.login();
        // 4、封装结果，返回给前端
        return null;
    }
}
