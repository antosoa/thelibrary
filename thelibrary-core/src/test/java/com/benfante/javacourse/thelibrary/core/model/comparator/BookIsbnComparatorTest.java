package com.benfante.javacourse.thelibrary.core.model.comparator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.benfante.javacourse.thelibrary.core.model.Book;

public class BookIsbnComparatorTest {

	@Test
	public void testCompareEquals() {
		BookIsbnComparator comparator = new BookIsbnComparator();
		Book b1 = new Book(0, "1111-0000-0000", null, null, null, null); 
		Book b2 = new Book(1, "1111-0000-0000", null, null, null, null);
		assertEquals(0, comparator.compare(b1, b2));
	}

	@Test
	public void testCompareLess() {
		BookIsbnComparator comparator = new BookIsbnComparator();
		Book b1 = new Book(0, "1111-0000-0000", null, null, null, null); 
		Book b2 = new Book(1, "1111-0000-0001", null, null, null, null);
		assertTrue(comparator.compare(b1, b2) < 0);
	}

	@Test
	public void testCompareGreater() {
		BookIsbnComparator comparator = new BookIsbnComparator();
		Book b1 = new Book(0, "1111-0000-0001", null, null, null, null); 
		Book b2 = new Book(1, "1111-0000-0000", null, null, null, null);
		assertTrue(comparator.compare(b1, b2) > 0);
	}
	
}
