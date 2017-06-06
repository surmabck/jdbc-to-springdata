package com.example.service;

import com.example.domain.Book;
import com.example.domain.User;

/**
 * Created by surmab on 06.04.2017.
 */
public interface RentBookService {

    boolean rentBook(User user, Book book);

    boolean returnBook(User user, Book book);
}
