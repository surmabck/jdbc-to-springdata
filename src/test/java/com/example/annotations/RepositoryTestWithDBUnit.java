package com.example.annotations;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.transaction.Transactional;
import java.lang.annotation.*;

/**
 * Created by surmab on 07.06.2017.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public @interface RepositoryTestWithDBUnit {
}
