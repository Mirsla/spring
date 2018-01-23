package com.smart.mirsla.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Mirsla on 2018/1/23.
 *
 * @Repository用于标注数据访问组件，即DAO组件
 */

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private final static String MATCH_COUNT_SQL = "SELECT COUNT(1) FROM t_user WHERE user_name = ? AND password = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password){
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName,password},Integer.class);
    }
}
