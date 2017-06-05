package com.example.repository;

import com.example.HsqlDatabase;
import com.example.domain.User;
import com.example.factory.DomainFactory;
import com.example.factory.UserFactory;
import com.example.mappers.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by surmab on 06.04.2017.
 */

@org.springframework.stereotype.Repository
@Log4j2
public class UserRepository extends Repository<User,Long> {
    public static final String INSERT_QUERY = "INSERT INTO USERS (ID,PASSWORD, USERNAME) VALUES(?, ?, ?)";
    public static final String FIND_QUERY = "SELECT * FROM USERS WHERE ID=?";
    public static final String FIND_ALL_QUERY = "SELECT * FROM USERS";
    public static final String DELETE_QUERY = "DELETE FROM USERS WHERE ID=?";

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_QUERY, new Object[] {id}, new UserMapper());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, new UserMapper());

    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(INSERT_QUERY, new Object[] {user.getId(), user.getPassword(), user.getUserName()});
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update(DELETE_QUERY, user.getId());
    }

}
