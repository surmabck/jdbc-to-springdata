package com.example.mappers;

import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by surmab on 05.06.2017.
 */
public class RentMapper implements RowMapper<Rent> {
    private Book book = new Book();
    private User user = new User();
    private Rent rent = new Rent();
    private ResultSet resultSet;
    @Override
    public Rent mapRow(ResultSet resultSet, int i) throws SQLException {
        setResultSet(resultSet);
        fillUser();
        fillBook();
        constructRent();
        return rent;
    }
    private void setResultSet(ResultSet resultSet){
        this.resultSet = resultSet;
    }
    private void fillUser() throws SQLException {
        Long userId = resultSet.getLong("USERID");
        String userPassword = resultSet.getString("PASSWORD");
        String userUserName = resultSet.getString("USERNAME");
        user.setPassword(userPassword).setUserName(userUserName).setId(userId);
    }
    private void fillBook() throws SQLException {
        String bookISBN = resultSet.getString("ISBN");
        String bookName = resultSet.getString("NAME");
        book.setISBN(bookISBN).setName(bookName);
    }
    private void constructRent() throws SQLException {
        Long rentsId = resultSet.getLong("ID");
        rent.setUser(user).setBook(book).setId(rentsId);
    }

}
