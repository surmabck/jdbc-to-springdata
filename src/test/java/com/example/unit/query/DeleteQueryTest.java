package com.example.unit.query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by bartosz on 2017-06-04.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class DeleteQueryTest {
    @Mock
    private Connection connection;
    @Mock
    private DataSource ds;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;

    @Before
    public void setUp() throws SQLException {
        assertNotNull(ds);
        when(connection.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getConnection()).thenReturn(connection);
    }
    @Test
    public void test1(){

    }
}
