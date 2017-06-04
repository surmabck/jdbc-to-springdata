package com.example;

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
public class RentsRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentRepository rentRepository;
    User user = User.builder().userName("USER1").password("USER1").id(0L).build();
    Book book1 = Book.builder().ISBN("ISBN1").name("BOOK1").build();
    Book book2 = Book.builder().ISBN("ISBN2").name("BOOK2").build();
    Rent rent1 = Rent.builder().book(book1).user(user).id(0L).build();
    Rent rent2 = Rent.builder().book(book2).user(user).id(1L).build();

    @Before
    public void setUp(){

        bookRepository.save(book1);
        bookRepository.save(book2);
        userRepository.save(user);
        rentRepository.save(rent1);
        rentRepository.save(rent2);
    }
    @After
    public void tearDown(){
        rentRepository.delete(rent1);
        rentRepository.delete(rent2);
        bookRepository.delete(book1);
        bookRepository.delete(book2);
        userRepository.delete(user);
    }


    @Test
    public void findAllTest() {
        List<Rent> allRents = rentRepository.findAll();
        assertEquals(2, allRents.size());

    }
    @Test
    public void findByIdTest(){
        Rent rent = rentRepository.findById(0L);
        assertEquals(book1,rent.getBook());
        assertEquals(user,rent.getUser());
    }
//    @Test
//    public void deleteTest(){
//        Book book = bookRepository.findById("ISBN1");
//        assertEquals("ISBN1",book.getId());
//        assertEquals("BOOK1",book.getName());
//        bookRepository.delete(book);
//        book = bookRepository.findById("ISBN1");
//        assertNull(book);
//
//    }
//    @Test
//    public void saveTest(){
//        Book book = bookRepository.findById("ISBN_TEST");
//        assertNull(book);
//        book = bookRepository.findById("ISBN_TEST");
//        assertEquals("ISBN_TEST",book.getISBN());
//        assertEquals("NAME_TEST",book.getName());
//    }

}
