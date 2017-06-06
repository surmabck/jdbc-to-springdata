package com.example.configuration;

import com.example.factory.BookFactory;
import com.example.factory.DomainFactory;
import com.example.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by surmab on 06.04.2017.
 */

@Configuration
public class ApplicationConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public BookFactory bookFactory() {
        return new BookFactory();
    }

    @Bean
    public DomainFactory userFactory(){
        return new UserFactory();
    }

}
