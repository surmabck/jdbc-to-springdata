package com.example.mappers;


import com.example.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by surmab on 05.06.2017.
 */
public class UserMapper implements RowMapper<User> {
    private User user = new User();
    private ResultSet resultSet;

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        setResultSet(resultSet);
        constructUser();
        return user;
    }
    private void setResultSet(ResultSet resultSet){
        this.resultSet = resultSet;
    }
    private void constructUser() throws SQLException {
        Long userID = resultSet.getLong("ID");
        String password = resultSet.getString("PASSWORD");
        String username = resultSet.getString("USERNAME");
        user.setId(userID);
        user.setPassword(password);
        user.setUserName(username);
    }
}
