package com.example;

import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import com.example.factory.RentFactory;
import com.example.repository.BookRepository;
import com.example.repository.RentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by surmab on 06.04.2017.
 */

@Component
@Order(0)
@Log4j2
@Profile("!test")
public class ExecuteCMD implements CommandLineRunner{


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RentRepository rentRepository;

    @Override
    public void run(String... strings) throws Exception {
        log.info(bookRepository.findAll());
        User user = new User().setUserName("USER11").setPassword("USER1");

        Book book4 = new Book().setIsbn("ISBN4").setName("BOOK4");

        Rent rent4 = new Rent().setBook(book4).setUser(user);
        rentRepository.save(rent4);
        log.info(rentRepository.findAll());

    }
}
