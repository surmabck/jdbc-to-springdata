package com.example.configuration;

import com.example.factory.BookFactory;
import com.example.factory.DomainFactory;
import com.example.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by surmab on 06.04.2017.
 */

@Configuration
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

    @Profile("!test")
    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
                .addScript("db_file.sql")
                .addScript("db_insert.sql")
                .build();
        return db;
    }
    @Profile("!test")
    @Bean
    public JdbcTemplate getJdbcTemplate(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
