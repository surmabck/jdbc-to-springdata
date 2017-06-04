package com.example.repository.query.find;

import com.example.domain.Book;
import com.example.factory.BookFactory;
import com.example.repository.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bartosz on 2017-06-04.
 */
public class FindBookById extends Query<Book>{
    private String idToFindFor;
    private BookFactory bookFactory;
    private Book book;
    public FindBookById(Connection conn){
        this(conn,"SELECT * FROM BOOKS WHERE ISBN=?");
    }
    public FindBookById(Connection conn, String query) {
        super(conn,query);
        bookFactory = new BookFactory();
    }

    public String getIdToFindFor() {
        return idToFindFor;
    }

    public void setIdToFindFor(String idToFindFor) {
        this.idToFindFor = idToFindFor;
    }

    @Override
    protected void preExecute() throws SQLException {
        book = bookFactory.create();
        getStatement().setString(1, idToFindFor);
    }

    @Override
    protected void executeStatement() throws SQLException {
        ResultSet resultSet = getStatement().executeQuery();
        if(resultSet.next()){
            String isbn = resultSet.getString("ISBN");
            String name = resultSet.getString("NAME");
            book.setISBN(isbn);
            book.setName(name);
            setResult(book);
        }
    }
}
