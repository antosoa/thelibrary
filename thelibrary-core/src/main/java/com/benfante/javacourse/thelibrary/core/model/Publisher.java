package com.benfante.javacourse.thelibrary.core.model;

import java.io.Serializable;

public class Publisher implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;

	public Publisher(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + "]";
	}

}
