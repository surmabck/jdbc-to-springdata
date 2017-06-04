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
public class BookRepository implements Repository<Book,String> {
    private static final String INSERT_QUERY = "INSERT INTO BOOKS(ISBN,Name) VALUES(?, ?)";
    private static final String FIND_QUERY = "SELECT * FROM BOOKS WHERE ISBN=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM BOOKS";
    private static final String DELETE_QUERY = "DELETE FROM BOOKS WHERE ISBN=?";

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
        try {
            st = db.conn.prepareStatement(FIND_QUERY);
            st.setString(1, id);
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next()){
                String isbn = resultSet.getString("ISBN");
                String name = resultSet.getString("NAME");
                Book book = bookFactory.createBook();
                book.setISBN(isbn);
                book.setName(name);
                return book;
            }

        } catch (SQLException e) {
            log.fatal("Query Failed : " + FIND_QUERY, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        PreparedStatement st = null;
        List<Book> books = new ArrayList<>();
        try {
            st = db.conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                String isbn = resultSet.getString(1);
                String name = resultSet.getString(2);
                Book book = bookFactory.createBook();
                book.setISBN(isbn);
                book.setName(name);
                books.add(book);
            }
            return books;

        } catch (SQLException e) {
            log.fatal("Query Failed : " + FIND_QUERY, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
        }
        return null;
    }

    @Override
    public boolean save(Book book) {
        PreparedStatement st = null;
        boolean ret = false;
        try {
            st = db.conn.prepareStatement(INSERT_QUERY);
            st.setString(1, book.getISBN());
            st.setString(2, book.getName());
            st.execute();
            ret = true;
        } catch (SQLException e) {
            log.fatal("Query Failed : " + INSERT_QUERY, e);
            ret = false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
            return ret;
        }
    }

    @Override
    public boolean delete(Book book) {
        PreparedStatement st = null;
        boolean ret = false;
        try {
            st = db.conn.prepareStatement(DELETE_QUERY);
            st.setString(1, book.getISBN());
            st.execute();
            ret = true;
        } catch (SQLException e) {
            log.fatal("Query Failed : " + DELETE_QUERY, e);
            ret = false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
            return ret;
        }
    }
}
