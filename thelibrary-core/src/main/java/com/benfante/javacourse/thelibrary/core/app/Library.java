package com.benfante.javacourse.thelibrary.core.app;

import java.util.Arrays;

import com.benfante.javacourse.thelibrary.core.model.*;

public class Library {

	private Book[] books = new Book[0];
	
	public void addBook(Book book) {
		books = addBook(books, book);
	}
	
	public void removeBook(Book book) {
		for (int i = 0; i < books.length; i++) {
			if (book.equals(books[i])) {
				Book[] newBooks = new Book[books.length-1];
				System.arraycopy(books, 0, newBooks, 0, i);
				System.arraycopy(books, i+1, newBooks, i, books.length-i-1);
				books = newBooks;
				break;
			}
		}
	}
	
	public void printBooks() {
		System.out.println(Arrays.toString(books));
	}
	
	public Book[] searchBooksByTitle(String title) {
		Book[] result = null;
		for (Book book : books) {
			if (title.equals(book.getTitle())) {
				result = addBook(result, book);
			}
		}
		return result;
	}

	public Book[] searchBooksByAuthor(Author author) {
		Book[] result = null;
		for (Book book : books) {
			if (book.hasAuthor(author)) {
				result = addBook(result, book);
			}
		}
		return result;
	}
	
	private Book[] addBook(Book[] result, Book book) {
		if (result == null) {
			result = new Book[1];
		} else {
			result = Arrays.copyOf(result, result.length+1);
		}
		result[result.length-1] = book;
		return result;
	}

	public static void main(String[] args) {
		Library app = new Library();
		
		Author author = new Author(0, "Agatha" , "Christie");
		Publisher publisher = new Publisher(0, "Mondadori");
		Book book1 = new Book(0, "Dieci Piccoli Indiani", new Author[]{author}, publisher, 10.5f);
		book1.addCategory(BookCategory.LITERATURE_AND_FICTION);
		Book book2 = new Book(1, "Assassinio sull'Orient Express", new Author[] {author}, publisher, 15.2f);
		book2.addCategory(BookCategory.LITERATURE_AND_FICTION);
		Author author2 = new Author(1, "J.K.", "Rowling");
		Publisher publisher2 = new Publisher(1, "Salani");
		Book book3 = new Book(2, "Harry Potter", new Author[] {author2}, publisher2, 15.45f);
		book3.addAuthor(new Author(3, "Andrea", "Camilleri"));
		book3.addCategory(BookCategory.LITERATURE_AND_FICTION);
		book3.addCategory(BookCategory.HISTORY);
		
		app.addBook(book1);
		app.addBook(book2);
		app.addBook(book3);
		app.printBooks();

		Book[] result1 = app.searchBooksByTitle("Harry Potter");
		System.out.println("*** Risultato della ricerca per titolo");
		System.out.println(Arrays.toString(result1));
		
		Book[] result2 = app.searchBooksByAuthor(new Author(0, null, null));
		System.out.println("*** Risultato della ricerca per authore");
		System.out.println(Arrays.toString(result2));
		
		app.removeBook(result1[0]);
		System.out.println("*** Dopo aver rimosso Harry Potter");
		app.printBooks();
		
	}

}