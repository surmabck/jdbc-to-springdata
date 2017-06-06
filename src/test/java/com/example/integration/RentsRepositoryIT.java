package com.example.integration;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

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
public class RentsRepositoryIT extends AbstractRepositoryIT{
//    @Autowired
//    private BookRepository bookRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RentRepository rentRepository;
//
//    User user = new User().setUserName("USER1").setPassword("USER1").setId(0L);
//    Book book1 = new Book().setIsbn("ISBN1").setName("BOOK1");
//    Book book2 = new Book().setIsbn("ISBN2").setName("BOOK2");
//    Rent rent1 = new Rent().setBook(book1).setUser(user).setId(0L);
//    Rent rent4 = new Rent().setBook(book2).setUser(user).setId(3L);
//
//    @Before
//    public void before(){
//        rentRepository.setJdbcTemplate(new JdbcTemplate(db));
//    }
//
//    @Test
//    public void findAllTest() {
//        List<Rent> allRents = rentRepository.findAll();
//        assertEquals(3, allRents.size());
//
//    }
//    @Test
//    public void findByIdTest(){
//        Rent rent = rentRepository.findById(0L);
//        assertEquals(book1,rent.getBook());
//        assertEquals(user,rent.getUser());
//    }
//    @Test(expected = EmptyResultDataAccessException.class)
//    public void deleteTest(){
//        rentRepository.delete(rent1);
//        rentRepository.findById(0L);
//    }
//    @Test
//    public void saveTest(){
//        rentRepository.save(rent4);
//    }

}
