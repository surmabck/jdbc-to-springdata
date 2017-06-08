package com.example.repository;

import com.example.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by surmab on 06.04.2017.
 */

@org.springframework.stereotype.Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbn(String isbn);
}
