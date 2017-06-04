package com.example;

import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import com.example.factory.BookFactory;
import com.example.factory.RentFactory;
import com.example.factory.UserFactory;
import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Created by surmab on 06.04.2017.
 */
@Component
@Order(-1)
@Profile("!test")
public class LoadDummyData implements CommandLineRunner {

    private UserRepository userRepository;
    private BookRepository bookRepository;
    private BookFactory bookFactory;
    @Autowired
    private RentFactory rentFactory;
    @Autowired
    private UserFactory userFactory;


    @Autowired
    public LoadDummyData(UserRepository userRepository, BookRepository bookRepository, BookFactory bookFactory) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookFactory = bookFactory;
    }

    @Override
    public void run(String... strings) throws Exception {
        Book book1 = bookFactory.create().setISBN("ISBN1").setName("BOOK1");
        Book book2 = bookFactory.create().setISBN("ISBN2").setName("BOOK2");
        Book book3 = bookFactory.create().setISBN("ISBN3").setName("BOOK3");
        User user1 = userFactory.create().setId(0L).setUserName("user1").setPassword("user1");
        User user2 = userFactory.create().setId(1L).setUserName("user2").setPassword("user3");
        User user3 = userFactory.create().setId(2L).setUserName("user3").setPassword("user3");
        Rent rent1 = rentFactory.create().setBook(book1).setUser(user1);
        Rent rent2 = rentFactory.create().setBook(book2).setUser(user2);
        Rent rent3 = rentFactory.create().setBook(book3).setUser(user3);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
