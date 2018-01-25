package com.smart.mirsla.service;

import com.smart.mirsla.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by Mirsla on 2018/1/25.
 */
@ContextConfiguration("classpath*:/smart-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","111");
        assertTrue(b1);
        assertTrue(b2,"看看能不能打印出来消息");
    }

    @Test
    public void findUserByUserName() {
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(),"admin","看看是否相等的啦");
    }

    @Test
    public void loginSuccess() {
        User user = new User();
        user.setCredits(0);
        user.setUserName("admin");
        user.setUserId(1);
        user.setLastIp("191.168.120.1");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
