package com.example.service;

import com.example.domain.Book;
import com.example.domain.User;

/**
 * Created by surmab on 06.04.2017.
 */
public interface RentBookService {

    public boolean rentBook(User user, Book book);

    public boolean returnBook(User user, Book book);
}
