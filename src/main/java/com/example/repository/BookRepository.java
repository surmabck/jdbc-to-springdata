package com.example.repository;

import com.example.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by surmab on 06.04.2017.
 */

@org.springframework.stereotype.Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbn(String isbn);
//    private HsqlDatabase db;
//
//    @Autowired
//    public BookRepository(HsqlDatabase db) {
//        this.db = db;
//    }
//
//    @Override
//    public Book findById(String id) {
//        FindBookById findBookById = new FindBookById(db.conn);
//        findBookById.setIdToFindFor(id);
//        findBookById.execute();
//        return findBookById.getResult();
//    }
//
//    @Override
//    public List<Book> findAll() {
//        FindAllBooks findAllBooks = new FindAllBooks(db.conn);
//        findAllBooks.execute();
//        return findAllBooks.getResult();
//    }
//
//    @Override
//    public void save(Book book) {
//        InsertBook bookInsert = new InsertBook(db.conn);
//        bookInsert.setBookToInsert(book);
//        bookInsert.execute();
//    }
//
//    @Override
//    public void delete(Book book) {
//        DeleteBook deleteBook = new DeleteBook(db.conn);
//        deleteBook.setBookToDelete(book);
//        deleteBook.execute();
//    }



}
