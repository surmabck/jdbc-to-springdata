package com.example.repository.query.insert;

import com.example.domain.Book;
import com.example.repository.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by bartosz on 2017-06-04.
 */
public class InsertBook extends Query<Book>{
    private Book bookToInsert;

    public InsertBook() {
        this(null);
    }

    public InsertBook(Connection conn){
        super(conn,"INSERT INTO BOOKS(ISBN,Name) VALUES(?, ?)");
    }

    public Book getBookToInsert() {
        return bookToInsert;
    }
    public void setBookToInsert(Book bookToInsert) {
        this.bookToInsert = bookToInsert;
    }

    @Override
    protected void preExecute() throws SQLException {
        getStatement().setString(1, this.bookToInsert.getISBN());
        getStatement().setString(2, this.bookToInsert.getName());
    }

    @Override
    protected void executeStatement() throws SQLException {
        getStatement().execute();
    }
}
