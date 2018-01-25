package com.smart.mirsla.dao;

import com.smart.mirsla.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Mirsla on 2018/1/25.
 */

@Repository
public class LoginLogDao {

    /**
     * 在这里测试不适用set方法是否能将jdbcTemplate注入
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String INSERT_LOGIN_LOG_SQL = "INSERT INTO t_login_log(user_id,ip,login_dateTime) VALUES (?,?,?)";

    public void insertLoginLog(LoginLog loginLog) {
        Object[] args = {loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()};

        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, args);
    }
}
