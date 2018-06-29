package com.benfante.javacourse.thelibrary.core.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

	@Test
	public void testBookConstruction() {
		long id = 0;
		String title = "A title";
		Author author = new Author(0, "The", "single author");
		Publisher publisher = new Publisher(0, "A publisher");
		float price = 1.23f;
		Book book = new Book(id, title, author, publisher, price);
		assertEquals(id, book.getId());
		assertEquals(title, book.getTitle());
		assertEquals(author, book.getAuthor());
		assertEquals(price, book.getPrice(), 0.1);
	}

	@Test
	public void testBookSetters() {
		long id = 0;
		String title = "A title";
		Author author = new Author(0, "The", "single author");
		Publisher publisher = new Publisher(0, "A publisher");
		float price = 1.23f;
		Book book = new Book(id, title, author, publisher, price);
		long newId = 1;
		String newTitle = "Another title";
		Author newAuthor = new Author(0, "Another", "author");
		Publisher newPublisher = new Publisher(0, "Another publisher");
		float newPrice = 2.34f;
		book.setId(newId);
		book.setTitle(newTitle);
		book.setAuthor(newAuthor);
		book.setPublisher(newPublisher);
		book.setPrice(newPrice);
		assertEquals(newId, book.getId());
		assertEquals(newTitle, book.getTitle());
		assertEquals(newAuthor, book.getAuthor());
		assertEquals(newPublisher, book.getPublisher());
		assertEquals(newPrice, book.getPrice(), 0.1);
	}
	
}
