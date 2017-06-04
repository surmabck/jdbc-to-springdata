package com.example.repository.query.find;

import com.example.domain.Book;
import com.example.factory.BookFactory;
import com.example.repository.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartosz on 2017-06-04.
 */
public class FindAllBooks extends Query<List<Book>>{
    private BookFactory bookFactory = null;

    public FindAllBooks(Connection conn){
        this(conn,"SELECT * FROM BOOKS");
    }

    public FindAllBooks(Connection conn, String query) {
        super(conn,query);
        bookFactory = new BookFactory();
    }

    public void setBookFactory(BookFactory bookFactory) {
        this.bookFactory = bookFactory;
    }
    public BookFactory getBookFactory() {
        return bookFactory;
    }
    @Override
    protected void preExecute() throws SQLException {

    }

    @Override
    protected void executeStatement() throws SQLException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = getStatement().executeQuery();
        while(resultSet.next()){
            Book book = getBook(resultSet);
            books.add(book);
        }
        setResult(books);
    }

    private Book getBook(ResultSet resultSet) throws SQLException {
        String isbn = resultSet.getString(1);
        String name = resultSet.getString(2);
        Book book = bookFactory.create();
        book.setISBN(isbn);
        book.setName(name);
        return book;
    }
}
