package com.example;

import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import com.example.factory.BookFactory;
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
    public LoadDummyData(UserRepository userRepository, BookRepository bookRepository, BookFactory bookFactory) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookFactory = bookFactory;
    }

    @Override
    public void run(String... strings) throws Exception {
        Book book1 = Book.builder().ISBN("ISBN1").name("BOOK1").build();
        Book book2 = Book.builder().ISBN("ISBN2").name("BOOK2").build();
        Book book3 = Book.builder().ISBN("ISBN3").name("BOOK3").build();
        User user1 = User.builder().id(0L).userName("user1").password("user1").build();
        User user2 = User.builder().id(1L).userName("user2").password("user3").build();
        User user3 = User.builder().id(2L).userName("user3").password("user3").build();
        Rent rent1 = Rent.builder().book(book1).user(user1).build();
        Rent rent2 = Rent.builder().book(book2).user(user2).build();
        Rent rent3 = Rent.builder().book(book3).user(user3).build();
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
