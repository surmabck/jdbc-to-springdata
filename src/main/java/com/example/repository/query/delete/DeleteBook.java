package com.example.repository.query.delete;

import com.example.domain.Book;
import com.example.repository.query.Query;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by bartosz on 2017-06-04.
 */
public class DeleteBook extends Query{
    private Book bookToDelete;
    public DeleteBook(Connection conn){
        super(conn,"DELETE FROM BOOKS WHERE ISBN=?");
    }

    public Book getBookToDelete() {
        return bookToDelete;
    }

    public void setBookToDelete(Book bookToDelete) {
        this.bookToDelete = bookToDelete;
    }

    @Override
    protected void preExecute() throws SQLException {
        getStatement().setString(1, getBookToDelete().getISBN());
    }

    @Override
    protected void executeStatement() throws SQLException {
        getStatement().execute();
    }
}
