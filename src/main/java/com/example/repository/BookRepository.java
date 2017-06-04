package com.example.repository;

import com.example.HsqlDatabase;
import com.example.domain.Book;
import com.example.factory.BookFactory;
import com.example.repository.query.delete.DeleteBook;
import com.example.repository.query.find.FindAllBooks;
import com.example.repository.query.find.FindBookById;
import com.example.repository.query.insert.InsertBook;
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

    private HsqlDatabase db;

    @Autowired
    public BookRepository(HsqlDatabase db) {
        this.db = db;
    }

    @Override
    public Book findById(String id) {
        FindBookById findBookById = new FindBookById(db.conn);
        findBookById.setIdToFindFor(id);
        findBookById.execute();
        return findBookById.getResult();
    }

    @Override
    public List<Book> findAll() {
        FindAllBooks findAllBooks = new FindAllBooks(db.conn);
        findAllBooks.execute();
        return findAllBooks.getResult();
    }

    @Override
    public void save(Book book) {
        InsertBook bookInsert = new InsertBook(db.conn);
        bookInsert.setBookToInsert(book);
        bookInsert.execute();
    }

    @Override
    public void delete(Book book) {
        DeleteBook deleteBook = new DeleteBook(db.conn);
        deleteBook.setBookToDelete(book);
        deleteBook.execute();
    }



}
