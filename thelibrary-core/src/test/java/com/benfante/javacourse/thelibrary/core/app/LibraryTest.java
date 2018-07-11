package com.benfante.javacourse.thelibrary.core.app;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import org.junit.Test;

import com.benfante.javacourse.thelibrary.core.model.Author;
import com.benfante.javacourse.thelibrary.core.model.Book;

public class LibraryTest {
	private PrintStream nullPrintStream = new PrintStream(
				new OutputStream() {
					@Override
					public void write(int b) throws IOException {
					}
				}
			);

	@Test
	public void testLoadBook1() throws IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/books.txt");
				Scanner scan = new Scanner(is);
		) {
			scan.useLocale(Locale.ENGLISH);
			assertNotNull("Can't find the input file for the test", is);
			Book book1 = app.loadBook(scan, nullPrintStream);
			assertNotNull(book1);
			assertEquals(1, book1.getId());
			assertEquals("0000-1111-2222", book1.getIsbn());
			assertEquals("Dieci Piccoli Indiani", book1.getTitle());
			assertEquals(BigDecimal.valueOf(10.5), book1.getPrice());
			assertEquals(1, book1.getAuthors().size());
			assertEquals(1, book1.getAuthors().get(0).getId());
			assertEquals("Agatha", book1.getAuthors().get(0).getFirstName());
			assertEquals("Christie", book1.getAuthors().get(0).getLastName());
			assertNotNull(book1.getPublisher());
			assertEquals(1, book1.getPublisher().getId());
			assertEquals("Mondadori", book1.getPublisher().getName());
		}
	}

	@Test
	public void testLoadAllBooks() throws IOException {
		Library app = new Library();
		loadAllBooks(app);
		assertNotNull(app.books);
		assertEquals(3, app.books.size());
	}

	private void loadAllBooks(Library app) throws IOException {
		try (InputStream is = this.getClass().getResourceAsStream("/books.txt");
				Scanner scan = new Scanner(is);
		) {
			scan.useLocale(Locale.ENGLISH);
			Book book = null;
			do {
				book = app.loadBook(scan, nullPrintStream);
			} while (book != null);
		}
	}
	
	@Test
	public void testLoadArchive() throws FileNotFoundException, ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		assertNotNull(app.books);
		assertEquals(3, app.books.size());
	}
	
	@Test
	public void testRemoveBook() throws ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		assertNotNull(app.books);
		int originalSize = app.books.size();
		Book containedBook = app.books.iterator().next();
		app.removeBook(new Book(containedBook.getId(), null, null));
		assertEquals(originalSize - 1, app.books.size());
	}

	@Test
	public void testAddExistentBook() throws ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		assertNotNull(app.books);
		int originalSize = app.books.size();
		Book containedBook = app.books.iterator().next();
		app.addBook(new Book(containedBook.getId(), null, null));
		assertEquals(originalSize, app.books.size());
	}

	@Test
	public void testSearchBookByTitle() throws ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		String title = "Dieci Piccoli Indiani";
		Book[] searchResult = app.searchBooksByTitle(title);
		assertEquals(1, searchResult.length);
		assertEquals(title, searchResult[0].getTitle());
	}

	@Test
	public void testSearchBookByTitleAfterRemove() throws ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		String title = "Dieci Piccoli Indiani";
		Book[] searchResult = app.searchBooksByTitle(title);
		app.removeBook(searchResult[0]);
		searchResult = app.searchBooksByTitle(title);
		assertEquals(0, searchResult.length);
	}
	
	@Test
	public void testSearchBookByAuthor() throws ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		Author author = new Author(1, "Agatha", "Christie");
		Book[] searchResult = app.searchBooksByAuthor(author);
		assertEquals(2, searchResult.length);
		assertTrue(searchResult[0].hasAuthor(author));
		assertTrue(searchResult[1].hasAuthor(author));
	}

	@Test
	public void testSearchBookByAuthorAfterRemove() throws ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		Author author = new Author(1, "Agatha", "Christie");
		Book[] searchResult = app.searchBooksByAuthor(author);
		app.removeBook(searchResult[0]);
		searchResult = app.searchBooksByAuthor(author);
		assertEquals(1, searchResult.length);
	}
	
	@Test
	public void testSearchBookByIsbn() throws ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		String isbn = "0000-1111-0000";
		Book searchResult = app.searchBooksByIsbn(isbn);
		assertNotNull(searchResult);
		assertEquals(isbn, searchResult.getIsbn());
	}

	@Test
	public void testSearchBookByIsbnAfterRemove() throws ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		String isbn = "0000-1111-0000";
		Book searchResult = app.searchBooksByIsbn(isbn);
		app.removeBook(searchResult);
		searchResult = app.searchBooksByIsbn(isbn);
		assertNull(searchResult);
	}
	
	@Test
	public void testAuthorsNotDuplicated() throws ClassNotFoundException, IOException {
		Library app = new Library();
		loadAllBooks(app);
		for(Author author: app.booksByAuthor.keySet()) {
			for(Book book: app.booksByAuthor.get(author)) {
				for (Author bookAuthor : book.getAuthors()) {
					if(bookAuthor.equals(author)) {
						assertTrue(bookAuthor == author);
					}
				}
			}
		}
	}
}
