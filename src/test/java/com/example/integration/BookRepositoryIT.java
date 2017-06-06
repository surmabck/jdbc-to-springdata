package com.example.integration;

import com.example.domain.Book;
import com.example.repository.BookRepository;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class})
@SpringBootTest
@ActiveProfiles("test")
public class BookRepositoryIT extends AbstractRepositoryIT {
	Book book4 = new Book().setISBN("ISBN_TEST").setName("NAME_TEST");

	@Autowired
	private BookRepository bookRepository;
	@Test
	public void findAllTest() {
		List<Book> allBooks = bookRepository.findAll();
		assertEquals(4, allBooks.size());
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
		bookRepository.save(book4);
		Book book = bookRepository.findById("ISBN_TEST");
		assertEquals("ISBN_TEST",book.getISBN());
		assertEquals("NAME_TEST",book.getName());
	}

}
