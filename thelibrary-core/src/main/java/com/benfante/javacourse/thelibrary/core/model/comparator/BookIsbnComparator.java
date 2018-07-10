package com.benfante.javacourse.thelibrary.core.model.comparator;

import java.util.Comparator;

import com.benfante.javacourse.thelibrary.core.model.Book;

public class BookIsbnComparator implements Comparator<Book> {

	@Override
	public int compare(Book o1, Book o2) {
		return o1.getIsbn().compareTo(o2.getIsbn());
	}

}
