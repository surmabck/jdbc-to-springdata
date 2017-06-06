package com.example.integration;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
public class UsersRepositoryIT extends AbstractRepositoryIT{
//    @Autowired
//    private UserRepository userRepository;
//
//    User user = new User().setUserName("USER1").setPassword("USER1").setId(0L);
//    User user4 = new User().setUserName("USER4").setPassword("USER4").setId(3L);
//
//    @Before
//    public void setUpRepo(){
//        userRepository.setJdbcTemplate(new JdbcTemplate(db));
//    }
//
//    @Test
//    public void findAllTest() {
//        List<User> allUsers = userRepository.findAll();
//        assertEquals(4,allUsers.size());
//    }
//    @Test
//    public void findByIdTest(){
//        User user = userRepository.findById(0L);
//        assertEquals(0L,(long)user.getId());
//        assertEquals("USER1",user.getPassword());
//        assertEquals("USER1",user.getUserName());
//    }
//    @Test(expected = EmptyResultDataAccessException.class)
//    public void deleteTest(){
//        User user = userRepository.findById(1000L);
//        assertEquals(1000L,(long)user.getId());
//        assertEquals("TO_DELETE",user.getPassword());
//        assertEquals("TO_DELETE",user.getUserName());
//        userRepository.delete(user);
//        userRepository.findById(1000L);
//    }
//    @Test
//    public void saveTest(){
//        userRepository.save(user4);
//        user = userRepository.findById(3L);
//        assertEquals(3L,(long)user.getId());
//        assertEquals("USER4",user.getPassword());
//        assertEquals("USER4",user.getUserName());
//    }
}
