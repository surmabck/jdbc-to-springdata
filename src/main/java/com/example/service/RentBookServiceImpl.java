package com.example.service;

import com.example.domain.Book;
import com.example.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by surmab on 06.04.2017.
 */

@Service
public class RentBookServiceImpl implements RentBookService {

    Map<Book, User> mapWithBookRent = new HashMap<>();

    @Override
    public boolean rentBook(User user, Book book) {
        User userWhoRent = mapWithBookRent.get(book);
        if(userWhoRent != null) {
            return false;
        }else{
            mapWithBookRent.put(book, user);
            return true;
        }


    }

    @Override
    public boolean returnBook(User user, Book book) {

        User remove = mapWithBookRent.remove(book);

        return remove != null;

    }
}
