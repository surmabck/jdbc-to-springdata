package com.example.mappers;

import com.example.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by surmab on 05.06.2017.
 */
public class BookMapper implements RowMapper<Book> {
    private Book book = new Book();
    private ResultSet resultSet;

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        setResultSet(resultSet);
        constructBook();
        return book;
    }
    private void setResultSet(ResultSet resultSet){
        this.resultSet = resultSet;
    }
    private void constructBook() throws SQLException {
        String isbn = resultSet.getString("ISBN");
        String name = resultSet.getString("NAME");
        book.setISBN(isbn).setName(name);
    }
}
