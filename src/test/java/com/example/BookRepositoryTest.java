package com.example;

import com.example.domain.Book;
import com.example.repository.BookRepository;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class})
@SpringBootTest
@ActiveProfiles("test")
public class BookRepositoryTest {
	Book book1 = Book.builder().ISBN("ISBN1").name("BOOK1").build();
	Book book2 = Book.builder().ISBN("ISBN2").name("BOOK2").build();
	Book book3 = Book.builder().ISBN("ISBN3").name("BOOK3").build();
	Book book4 = Book.builder().ISBN("ISBN_TEST").name("NAME_TEST").build();

	@Before
	public void setUp(){

		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
	}
	@After
	public void tearDown(){
		bookRepository.delete(book1);
		bookRepository.delete(book2);
		bookRepository.delete(book3);
		bookRepository.delete(book4);
	}
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void findAllTest() {
		List<Book> allBooks = bookRepository.findAll();
		assertEquals(3, allBooks.size());
		assertEquals("ISBN1",allBooks.get(0).getId());
		assertEquals("ISBN2",allBooks.get(1).getId());
		assertEquals("ISBN3",allBooks.get(2).getId());
		assertEquals("ISBN1",allBooks.get(0).getISBN());
		assertEquals("ISBN2",allBooks.get(1).getISBN());
		assertEquals("ISBN3",allBooks.get(2).getISBN());
		assertEquals("BOOK1",allBooks.get(0).getName());
		assertEquals("BOOK2",allBooks.get(1).getName());
		assertEquals("BOOK3",allBooks.get(2).getName());
	}
	@Test
	public void findByIdTest(){
		Book book = bookRepository.findById("ISBN1");
		assertEquals("ISBN1",book.getId());
		assertEquals("BOOK1",book.getName());
	}
	@Test
	public void deleteTest(){
		Book book = bookRepository.findById("ISBN1");
		assertEquals("ISBN1",book.getId());
		assertEquals("BOOK1",book.getName());
		bookRepository.delete(book);
		book = bookRepository.findById("ISBN1");
		assertNull(book);

	}
	@Test
	public void saveTest(){
		Book book = bookRepository.findById("ISBN_TEST");
		assertNull(book);
		bookRepository.save(book4);
		book = bookRepository.findById("ISBN_TEST");
		assertEquals("ISBN_TEST",book.getISBN());
		assertEquals("NAME_TEST",book.getName());
	}

}
