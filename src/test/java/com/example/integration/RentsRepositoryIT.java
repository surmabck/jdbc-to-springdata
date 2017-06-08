package com.example.integration;

import com.example.annotations.RepositoryTestWithDBUnit;
import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import com.example.repository.BookRepository;
import com.example.repository.RentRepository;
import com.example.repository.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Before;
import org.junit.BeforeClass;
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

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by surmab on 01.06.2017.
 */
@RepositoryTestWithDBUnit
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseSetup(value = "/RentRepositoryTest/sampleData.xml",type= DatabaseOperation.CLEAN_INSERT)
public class RentsRepositoryIT{
    private static User user2;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentRepository rentRepository;
    private static Book book4;
    private static User user = null;
    private static Book book1;
    private static Book book2;
    private static Book book3;
    private static Rent rent1;
    private static Rent rent2;
    private static Rent rent3;
    private static Rent rent4;
    @BeforeClass
    public static void setup(){
         user = new User().setUserName("USER1").setPassword("USER1").setId(1000L);
         user2 = new User().setUserName("USER45").setPassword("USER45");
         book1 = new Book().setIsbn("ISBN1").setName("BOOK1");
         book2 = new Book().setIsbn("ISBN2").setName("BOOK2");
         book3 = new Book().setIsbn("ISBN3").setName("BOOK3");
         book4 = new Book().setIsbn("ISBN45").setName("BOOK45");
         rent1 = new Rent().setBook(book1).setUser(user).setId(1000L);
         rent2 = new Rent().setBook(book2).setUser(user).setId(1001L);
         rent3 = new Rent().setBook(book3).setUser(user).setId(1002L);
         rent4 = new Rent().setBook(book4).setUser(user2);
         user.getBookRents().add(rent1);
         user.getBookRents().add(rent2);
         user.getBookRents().add(rent3);
    }


    @Test
    public void findAllTest() {
        List<Rent> allRents = rentRepository.findAll();
        assertEquals(3, allRents.size());
    }
    @Test
    public void findByIdTest(){
        Rent rent = rentRepository.findById(1000L);
        assertEquals(book1,rent.getBook());
        assertEquals(user,rent.getUser());
    }
    @ExpectedDatabase("/RentRepositoryTest/deleteTest.xml")
    @Test
    public void deleteTest(){
        rentRepository.delete(rent1);
        rentRepository.flush();
    }

    @ExpectedDatabase(value = "/RentRepositoryTest/saveTest.xml")
    @Test
    public void saveTest(){
        rentRepository.save(rent4);
        rentRepository.flush();
    }

}
