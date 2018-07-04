package com.benfante.javacourse.thelibrary.core.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AuthorTest {

	@Test
	public void testEquals() {
		Author author1 = new Author(1, null, null);
		Author author2 = new Author(1, null, null);
		assertTrue(author1.equals(author2));
	}

	@Test
	public void testEqualsFalse() {
		Author author1 = new Author(1, null, null);
		Author author2 = new Author(2, null, null);
		assertFalse(author1.equals(author2));
	}

	@Test
	public void testHashCode() {
		Author author1 = new Author(1, null, null);
		Author author2 = new Author(1, null, null);
		assertTrue(author1.hashCode() == author2.hashCode());
	}

	@Test
	public void testHashCodeDifferentMaybe() {
		Author author1 = new Author(1, null, null);
		Author author2 = new Author(2, null, null);
		assertTrue(author1.hashCode() != author2.hashCode());
	}
	
}
