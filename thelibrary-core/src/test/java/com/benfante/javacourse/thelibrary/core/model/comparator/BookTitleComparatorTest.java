package com.benfante.javacourse.thelibrary.core.model.comparator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.benfante.javacourse.thelibrary.core.model.Book;

public class BookTitleComparatorTest {

	@Test
	public void testCompareEquals() {
		BookTitleComparator comparator = new BookTitleComparator();
		Book b1 = new Book(0, "1111-0000-0000", "Title 0", null, null, null); 
		Book b2 = new Book(1, "1111-0000-0000", "Title 0", null, null, null);
		assertEquals(0, comparator.compare(b1, b2));
	}

	@Test
	public void testCompareLess() {
		BookTitleComparator comparator = new BookTitleComparator();
		Book b1 = new Book(1, "1111-0000-0001", "Title 0", null, null, null); 
		Book b2 = new Book(0, "1111-0000-0000", "Title 1", null, null, null);
		assertTrue(comparator.compare(b1, b2) < 0);
	}

	@Test
	public void testCompareGreater() {
		BookTitleComparator comparator = new BookTitleComparator();
		Book b1 = new Book(0, "1111-0000-0000", "Title 1", null, null, null); 
		Book b2 = new Book(1, "1111-0000-0001", "Title 0", null, null, null);
		assertTrue(comparator.compare(b1, b2) > 0);
	}
	
}
