package com.example.integration;

import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import com.example.repository.BookRepository;
import com.example.repository.RentRepository;
import com.example.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by surmab on 01.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@SpringBootTest
@ActiveProfiles("test")
public class RentsRepositoryIT {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentRepository rentRepository;
    private EmbeddedDatabase db;

    User user = new User().setUserName("USER1").setPassword("USER1").setId(0L);
    Book book1 = new Book().setISBN("ISBN1").setName("BOOK1");
    Book book2 = new Book().setISBN("ISBN2").setName("BOOK2");
    Rent rent1 = new Rent().setBook(book1).setUser(user).setId(0L);
    Rent rent2 = new Rent().setBook(book2).setUser(user).setId(1L);
    Rent rent3 = new Rent().setBook(book2).setUser(user).setId(2L);
    Rent rent4 = new Rent().setBook(book2).setUser(user).setId(3L);

    @Before
    public void setUp(){
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db_file.sql")
                .addScript("db_insert.sql")
                .build();
        rentRepository.setJdbcTemplate(new JdbcTemplate(db));
    }
    @After
    public void tearDown(){
        db.shutdown();

    }

    @Test
    public void findAllTest() {
        List<Rent> allRents = rentRepository.findAll();
        assertEquals(3, allRents.size());

    }
    @Test
    public void findByIdTest(){
        Rent rent = rentRepository.findById(0L);
        assertEquals(book1,rent.getBook());
        assertEquals(user,rent.getUser());
    }
    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteTest(){
        rentRepository.delete(rent1);
        rentRepository.findById(0L);
    }
    @Test
    public void saveTest(){
        rentRepository.save(rent4);
    }

}