package com.example;

import com.example.repository.BookRepository;
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


    @Override
    public void run(String... strings) throws Exception {
        log.info(bookRepository.findAll());
    }
}
