package com.example.integration;

import com.example.configuration.ApplicationConfiguration;
import com.example.domain.Book;
import com.example.repository.BookRepository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class})
@Transactional
public class BookRepositoryIT{
	Book book4 = new Book().setIsbn("ISBN_TEST").setName("NAME_TEST");

	@Autowired
	private BookRepository bookRepository;

	@DatabaseSetup("/META-INF/dbtest/sampleData.xml")
	@Test
	public void findAllTest() {

		List<Book> allBooks = bookRepository.findAll();
		assertEquals(4, allBooks.size());
	}
	@Test
	public void findByIdTest(){
		Book book = bookRepository.findByIsbn("ISBN1");
		assertEquals("ISBN1",book.getIsbn());
		assertEquals("BOOK1",book.getName());
	}
//	@Test
//	public void deleteTest(){
//		Book book = bookRepository.findById("ISBN1");
//		assertEquals("ISBN1",book.getId());
//		assertEquals("BOOK1",book.getName());
//		bookRepository.delete(book);
//		book = bookRepository.findById("ISBN1");
//		assertNull(book);
//
//	}
//	@Test
//	public void saveTest(){
//		bookRepository.save(book4);
//		Book book = bookRepository.findById("ISBN_TEST");
//		assertEquals("ISBN_TEST",book.getIsbn());
//		assertEquals("NAME_TEST",book.getName());
//	}

}
