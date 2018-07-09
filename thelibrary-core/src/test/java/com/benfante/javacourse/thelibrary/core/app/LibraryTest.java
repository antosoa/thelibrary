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

import org.junit.Ignore;
import org.junit.Test;

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
		try (InputStream is = this.getClass().getResourceAsStream("/books.txt");
				Scanner scan = new Scanner(is);
		) {
			scan.useLocale(Locale.ENGLISH);
			Book book = null;
			do {
				book = app.loadBook(scan, nullPrintStream);
			} while (book != null);
			assertNotNull(app.books);
			assertEquals(2, app.books.size());
		}
	}
	
	@Test
	public void testLoadArchive() throws FileNotFoundException, ClassNotFoundException, IOException {
		Library app = new Library();
		try (InputStream is = this.getClass().getResourceAsStream("/archive.dat");) {
			app.loadArchive(is);
		}
		assertNotNull(app.books);
		assertEquals(2, app.books.size());
	}
	
	@Ignore("Not yet...the solution in the next exercise")
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

}
