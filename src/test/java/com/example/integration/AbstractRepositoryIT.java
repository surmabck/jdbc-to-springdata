package com.example.integration;

import org.junit.After;
import org.junit.Before;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by surmab on 05.06.2017.
 */
public class AbstractRepositoryIT {
    protected EmbeddedDatabase db;
    @Before
    public void setUp(){
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db_file.sql")
                .addScript("db_insert.sql")
                .build();
    }
    @After
    public void tearDown(){
        db.shutdown();
    }
}
