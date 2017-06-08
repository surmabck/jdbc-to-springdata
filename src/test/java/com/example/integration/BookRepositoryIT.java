package com.example.integration;

import com.example.annotations.RepositoryTestWithDBUnit;
import com.example.domain.Book;
import com.example.repository.BookRepository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
@RepositoryTestWithDBUnit
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseSetup(value = "/BookRepositoryTest/sampleData.xml", type= DatabaseOperation.CLEAN_INSERT)
public class BookRepositoryIT {
	Book bookToDelete = new Book().setIsbn("TO_DELETE").setName("TO_DELETE");
	Book bookToSave = new Book().setIsbn("TO_INSERT").setName("TO_INSERT");

	@Autowired
	private BookRepository bookRepository;

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

	@ExpectedDatabase("/BookRepositoryTest/deleteTest.xml")
	@Test
	public void deleteTest(){
		bookRepository.delete(bookToDelete);
		bookRepository.flush();
	}

	@ExpectedDatabase("/BookRepositoryTest/saveTest.xml")
	@Test
	public void saveTest(){
		bookRepository.save(bookToSave);
		bookRepository.flush();
	}

}
