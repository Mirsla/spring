package com.smart.mirsla.service;

import com.smart.mirsla.dao.LoginLogDao;
import com.smart.mirsla.dao.UserDao;
import com.smart.mirsla.domain.LoginLog;
import com.smart.mirsla.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mirsla on 2018/1/25.
 */
@Service
public class UserService {

    /**
     * @Autowired默认是类型匹配 spring
     * @Resource后面没有任何内容，默认通过name属性去匹配bean，找不到再按type去匹配 javaee
     */
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String userName, String password) {
        int matchUser = userDao.getMatchCount(userName, password);
        return matchUser > 0;
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
