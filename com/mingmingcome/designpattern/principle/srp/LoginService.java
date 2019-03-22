package com.mingmingcome.designpattern.principle.srp;

public class LoginService {

    private UserDao userDao;

    public boolean login() {
        userDao.findOne();
        // 3、比较数据，得出结果
        return false;
    }
}
