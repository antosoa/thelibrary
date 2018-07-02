package com.benfante.javacourse.thelibrary.core.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	@Test
	public void testBookConstruction() {
		long id = 0;
		String title = "A title";
		Author[] authors = new Author[] {new Author(0, "The", "single author")};
		Publisher publisher = new Publisher(0, "A publisher");
		float price = 1.23f;
		Book book = new Book(id, title, authors, publisher, price);
		assertEquals(id, book.getId());
		assertEquals(title, book.getTitle());
		assertArrayEquals(authors, book.getAuthors());
		assertEquals(price, book.getPrice(), 0.1);
	}

	@Test
	public void testBookSetters() {
		long id = 0;
		String title = "A title";
		Author[] authors = new Author[] {new Author(0, "The", "single author")};
		Publisher publisher = new Publisher(0, "A publisher");
		float price = 1.23f;
		Book book = new Book(id, title, authors, publisher, price);
		long newId = 1;
		String newTitle = "Another title";
		Author[] newAuthors = new Author[] {new Author(0, "Another", "author")};
		Publisher newPublisher = new Publisher(0, "Another publisher");
		float newPrice = 2.34f;
		book.setId(newId);
		book.setTitle(newTitle);
		book.setAuthors(newAuthors);
		book.setPublisher(newPublisher);
		book.setPrice(newPrice);
		assertEquals(newId, book.getId());
		assertEquals(newTitle, book.getTitle());
		assertArrayEquals(newAuthors, book.getAuthors());
		assertEquals(newPublisher, book.getPublisher());
		assertEquals(newPrice, book.getPrice(), 0.1);
	}
	
	@Test
	public void testAddAuthor() {
		long id = 0;
		String title = "A title";
		Author[] authors = new Author[] {new Author(0, "The", "single author")};
		Publisher publisher = new Publisher(0, "A publisher");
		float price = 1.23f;
		Book book = new Book(id, title, authors, publisher, price);
		book.addAuthor(new Author(1, "Another", "Author"));
		assertEquals(2, book.getAuthors().length);
		assertEquals(1L, book.getAuthors()[1].getId());
	}
	
}
