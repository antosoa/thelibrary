package com.benfante.javacourse.thelibrary.core.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class BookTest {

	@Test
	public void testBookConstruction() {
		long id = 0;
		String title = "A title";
		List<Author> authors = new LinkedList<>();
		authors.add(new Author(0, "The", "single author"));
		Publisher publisher = new Publisher(0, "A publisher");
		BigDecimal price = BigDecimal.valueOf(1.23);
		Book book = new Book(id, title, authors, publisher, price);
		assertEquals(id, book.getId());
		assertEquals(title, book.getTitle());
		assertEquals(authors, book.getAuthors());
		assertEquals(price, book.getPrice());
	}

	@Test
	public void testBookSetters() {
		long id = 0;
		String title = "A title";
		List<Author> authors = new LinkedList<>();
		authors.add(new Author(0, "The", "single author"));
		Publisher publisher = new Publisher(0, "A publisher");
		BigDecimal price = BigDecimal.valueOf(1.23);
		Book book = new Book(id, title, authors, publisher, price);
		long newId = 1;
		String newTitle = "Another title";
		List<Author> newAuthors = new LinkedList<>();
		authors.add(new Author(1, "Another", "author"));
		Publisher newPublisher = new Publisher(0, "Another publisher");
		BigDecimal newPrice = BigDecimal.valueOf(2.34);
		book.setId(newId);
		book.setTitle(newTitle);
		book.setAuthors(newAuthors);
		book.setPublisher(newPublisher);
		book.setPrice(newPrice);
		assertEquals(newId, book.getId());
		assertEquals(newTitle, book.getTitle());
		assertEquals(newAuthors, book.getAuthors());
		assertEquals(newPublisher, book.getPublisher());
		assertEquals(newPrice, book.getPrice());
	}
	
	@Test
	public void testAddAuthor() {
		long id = 0;
		String title = "A title";
		List<Author> authors = new LinkedList<>();
		authors.add(new Author(0, "The", "single author"));
		Publisher publisher = new Publisher(0, "A publisher");
		BigDecimal price = BigDecimal.valueOf(1.23);
		Book book = new Book(id, title, authors, publisher, price);
		book.addAuthor(new Author(1, "Another", "Author"));
		assertEquals(2, book.getAuthors().size());
		assertEquals(1L, book.getAuthors().get(1).getId());
	}

	@Test
	public void testAddCategory() {
		long id = 0;
		String title = "A title";
		List<Author> authors = new LinkedList<>();
		authors.add(new Author(0, "The", "single author"));
		Publisher publisher = new Publisher(0, "A publisher");
		BigDecimal price = BigDecimal.valueOf(1.23);
		Book book = new Book(id, title, authors, publisher, price);
		book.addCategory(BookCategory.COMPUTERS_AND_TECHNOLOGY);
		book.addCategory(BookCategory.OTHER);
		book.addCategory(BookCategory.COMPUTERS_AND_TECHNOLOGY);
		BookCategory[] categories = book.getCategories();
		assertEquals(2, categories.length);
		assertEquals(BookCategory.OTHER, categories[0]);
	}
	
}
