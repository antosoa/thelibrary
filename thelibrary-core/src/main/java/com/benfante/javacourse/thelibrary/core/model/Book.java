package com.benfante.javacourse.thelibrary.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Book implements Serializable, Comparable<Book> {
	private static final long serialVersionUID = 3L;
	private long id;
	private String isbn;
	private String title;
	private BigDecimal price;
	private List<Author> authors = new ArrayList<>();
	private Publisher publisher;
	private BookCategory[] categories;
	private static final Logger log = LoggerFactory.getLogger(Book.class);

	public Book(long id, String title, List<Author> authors) {
		this.id = id;
		this.title = title;
		if (authors != null) {
			this.authors = authors;
		}
	}

	public Book(long id, String title, List<Author> authors, BigDecimal price) {
		this(id, title, authors);
		this.price = price;
	}

	public Book(long id, String title, List<Author> authors, Publisher publisher, BigDecimal price) {
		this(id, title, authors, price);
		this.publisher = publisher;
	}

	public Book(long id, String isbn, String title, List<Author> authors, Publisher publisher, BigDecimal price) {
		this(id, title, authors, publisher, price);
		this.isbn = isbn;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		if (authors != null) {
			this.authors = authors;
		}
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public BookCategory[] getCategories() {
		return categories;
	}

	public void setCategories(BookCategory[] categories) {
		this.categories = categories;
	}

	public void addAuthor(Author author) {
		log.debug("Adding author with id={}, firstName={}, lastName={}",
				author.getId(), author.getFirstName(), author.getLastName());
		this.authors.add(author);
	}

	public boolean hasAuthor(Author author) {
		return this.authors.contains(author);
	}

	public void addCategory(BookCategory category) {
		log.debug("Adding category {} to the book with id {}.", category, this.id);
		if (categories == null) {
			categories = new BookCategory[1];
		} else {
			categories = Arrays.copyOf(categories, categories.length + 1);
		}
		categories[categories.length - 1] = category;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", price=" + price + ", authors=" + authors
				+ ", publisher=" + publisher + ", categories=" + Arrays.toString(categories) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Book))
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Book o) {
		return (int) (this.id - o.id);
	}
	
	

}
