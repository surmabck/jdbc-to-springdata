package com.example.integration;

import com.example.annotations.RepositoryTestWithDBUnit;
import com.example.domain.User;
import com.example.repository.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
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

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by surmab on 01.06.2017.
 */
@RepositoryTestWithDBUnit
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseSetup(value = "/UserRepositoryTest/sampleData.xml", type= DatabaseOperation.CLEAN_INSERT)
public class UsersRepositoryIT{
    @Autowired
    private UserRepository userRepository;

    User user = new User().setUserName("USER1").setPassword("USER1").setId(1000L);
    User user4 = new User().setUserName("USER4").setPassword("USER4").setId(3L);

    @Test
    public void findAllTest() {
        List<User> allUsers = userRepository.findAll();
        assertEquals(4,allUsers.size());
    }
    @Test
    public void findByIdTest(){
        User user = userRepository.findById(1000L);
        assertEquals(1000L,(long)user.getId());
        assertEquals("USER1",user.getPassword());
        assertEquals("USER1",user.getUserName());
    }
    @ExpectedDatabase("/UserRepositoryTest/deleteTest.xml")
    @Test
    public void deleteTest(){

        userRepository.delete(user);
        userRepository.flush();
    }
    @Test
    public void saveTest(){
        userRepository.save(user4);
        userRepository.flush();
    }
}
