package com.example.repository;

import com.example.HsqlDatabase;
import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import com.example.factory.BookFactory;
import com.example.factory.DomainFactory;
import com.example.factory.RentFactory;
import com.example.factory.UserFactory;
import com.example.mappers.RentMapper;
import com.example.mappers.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by surmab on 31.05.2017.
 */
@Log4j2
@org.springframework.stereotype.Repository
public class RentRepository extends Repository<Rent,Long> {
    public static final String INSERT_QUERY = "INSERT INTO RENTS(ID,BOOKISBN,USERID) VALUES(?, ?, ?)";
    public static final String FIND_QUERY = "SELECT RENTS.ID as ID, BOOKS.ISBN as ISBN, BOOKS.Name as NAME, USERS.ID as USERID, USERS.PASSWORD as PASSWORD, USERS.USERNAME as USERNAME FROM RENTS JOIN BOOKS ON RENTS.BOOKISBN = BOOKS.ISBN JOIN USERS ON RENTS.USERID = USERS.ID WHERE RENTS.ID = ?";
    public static final String FIND_ALL_QUERY = "SELECT RENTS.ID as ID, BOOKS.ISBN as ISBN, BOOKS.Name as NAME, USERS.ID as USERID, USERS.PASSWORD as PASSWORD, USERS.USERNAME as USERNAME FROM RENTS JOIN BOOKS ON RENTS.BOOKISBN = BOOKS.ISBN JOIN USERS ON RENTS.USERID = USERS.ID";
    public static final String DELETE_QUERY = "DELETE FROM RENTS WHERE ID=?";

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Rent findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_QUERY, new Object[] {id}, new RentMapper());
    }

    @Override
    public List<Rent> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, new RentMapper());
    }

    @Override
    public void save(Rent rent) {
        jdbcTemplate.update(INSERT_QUERY, new Object[] {rent.getId(), rent.getBook().getISBN(), rent.getUser().getId()});
    }

    @Override
    public void delete(Rent rent) {
        jdbcTemplate.update(DELETE_QUERY, rent.getId());
    }

}
