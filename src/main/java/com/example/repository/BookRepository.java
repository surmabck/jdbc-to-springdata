package com.example.repository;

import com.example.HsqlDatabase;
import com.example.domain.Book;
import com.example.factory.BookFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by surmab on 06.04.2017.
 */

@Log4j2
@org.springframework.stereotype.Repository
public class BookRepository extends Repository<Book,String> {
    public static final String INSERT_QUERY = "INSERT INTO BOOKS(ISBN,Name) VALUES(?, ?)";
    public static final String FIND_QUERY = "SELECT * FROM BOOKS WHERE ISBN=?";
    public static final String FIND_ALL_QUERY = "SELECT * FROM BOOKS";
    public static final String DELETE_QUERY = "DELETE FROM BOOKS WHERE ISBN=?";

    private HsqlDatabase db;
    private BookFactory bookFactory;

    @Autowired
    public BookRepository(HsqlDatabase db, BookFactory bookFactory ) {
        this.db = db;
        this.bookFactory = bookFactory;
    }

    @Override
    public Book findById(String id) {

        PreparedStatement st = null;
        Book book = null;
        try {
            st = db.conn.prepareStatement(FIND_QUERY);
            book = findBookById(id, st);
        } catch (SQLException e) {
            logError(e,FIND_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }

            }
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        PreparedStatement st = null;
        List<Book> books = null;
        try {
            st = db.conn.prepareStatement(FIND_ALL_QUERY);
            books = findAllBooks(st);
        } catch (SQLException e) {
            logError(e, FIND_ALL_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }

            }
        }
        return books;
    }

    @Override
    public void save(Book book) {
        PreparedStatement st = null;
        try {
            st = db.conn.prepareStatement(INSERT_QUERY);
            saveBook(book, st);
        } catch (SQLException e) {
            logError(e,INSERT_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }

            }
        }
    }

    @Override
    public void delete(Book book) {
        PreparedStatement st = null;
        try {
            st = db.conn.prepareStatement(DELETE_QUERY);
            deleteBook(book, st);
        } catch (SQLException e) {
            logError(e,DELETE_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }
            }
        }
    }

    private List<Book> findAllBooks(PreparedStatement st) throws SQLException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = st.executeQuery();
        while(resultSet.next()){
            String isbn = resultSet.getString(1);
            String name = resultSet.getString(2);
            Book book = bookFactory.create();
            book.setISBN(isbn);
            book.setName(name);
            books.add(book);
        }
        return books;
    }

    private void saveBook(Book book, PreparedStatement st) throws SQLException {
        st.setString(1, book.getISBN());
        st.setString(2, book.getName());
        st.execute();
    }

    private void deleteBook(Book book, PreparedStatement st) throws SQLException {
        st.setString(1, book.getISBN());
        st.execute();
    }

    private Book findBookById(String id, PreparedStatement st) throws SQLException {
        st.setString(1, id);
        ResultSet resultSet = st.executeQuery();
        if(resultSet.next()){
            String isbn = resultSet.getString("ISBN");
            String name = resultSet.getString("NAME");
            Book book = bookFactory.create();
            book.setISBN(isbn);
            book.setName(name);
            return book;
        }
        return null;
    }
}
