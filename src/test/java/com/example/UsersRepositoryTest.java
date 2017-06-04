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
public class UsersRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    User user = User.builder().userName("USER1").password("USER1").id(0L).build();
    User user2 = User.builder().userName("USER2").password("USER2").id(1L).build();
    User user3 = User.builder().userName("USER3").password("USER3").id(2L).build();
    User user4 = User.builder().userName("USER4").password("USER4").id(3L).build();

    @Before
    public void setUp(){
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
    }
    @After
    public void tearDown(){

        userRepository.delete(user);
        userRepository.delete(user2);
        userRepository.delete(user3);
        userRepository.delete(user4);
    }

    @Test
    public void findAllTest() {
        List<User> allUsers = userRepository.findAll();
        assertEquals(3,allUsers.size());
        assertEquals(user,allUsers.get(0));
        assertEquals(user2,allUsers.get(1));
        assertEquals(user3,allUsers.get(2));
    }
    @Test
    public void findByIdTest(){
        User user = userRepository.findById(0L);
        assertEquals(0L,(long)user.getId());
        assertEquals("USER1",user.getPassword());
        assertEquals("USER1",user.getUserName());
    }
    @Test
    public void deleteTest(){
        User user = userRepository.findById(0L);
        assertEquals(0L,(long)user.getId());
        assertEquals("USER1",user.getPassword());
        assertEquals("USER1",user.getUserName());
        userRepository.delete(user);
        user = userRepository.findById(0L);
        assertNull(user);

    }
    @Test
    public void saveTest(){
        User user = userRepository.findById(3L);
        assertNull(user);
        userRepository.save(user4);
        user = userRepository.findById(3L);
        assertEquals(3L,(long)user.getId());
        assertEquals("USER4",user.getPassword());
        assertEquals("USER4",user.getUserName());
    }
}
