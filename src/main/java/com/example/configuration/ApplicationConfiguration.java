package com.example.configuration;

import com.example.factory.BookFactory;
import com.example.factory.DomainFactory;
import com.example.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by surmab on 06.04.2017.
 */

@Configuration
@Import({BookConfiguration.class, UserConfiguration.class})
public class ApplicationConfiguration {

    @Autowired
    private BookConfiguration bookConfiguration;

    @Autowired
    private UserConfiguration userConfiguration;

    @Bean
    public BookFactory bookFactory() {
        return new BookFactory();
    }

    @Bean
    public DomainFactory userFactory(){
        return new UserFactory();
    }
}
