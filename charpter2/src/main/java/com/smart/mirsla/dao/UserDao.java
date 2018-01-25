package com.smart.mirsla.dao;

import com.smart.mirsla.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mirsla on 2018/1/23.
 *
 * @Repository用于标注数据访问组件，即DAO组件
 */

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private final static String MATCH_COUNT_SQL = "SELECT COUNT(1) FROM t_user WHERE user_name = ? AND password = ?";

    private final static String QUERY_BY_USERNAME = "SELECT user_id,user_name,credits FROM t_user WHERE user_name = ?";

    private final static String UPDATA_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit = ?, last_ip = ?, credits = ? WHERE user_id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 通过用户名和密码判断当前用户是否存在
     *
     * @param userName
     * @param password
     * @return
     */
    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    /**
     * 通过UserName查找user
     *
     * @param userName
     * @return
     */
    public User findUserByUserName(final String userName) {
        final User user = new User();
        jdbcTemplate.query(QUERY_BY_USERNAME, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString("user_name"));
                        user.setCredits(resultSet.getInt("credits"));
                    }
                });
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATA_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIp(),
                user.getCredits(), user.getUserId()});

    }
}
