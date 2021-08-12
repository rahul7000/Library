package com.rahul.library.requests;

public class BookCreationRequest {
	private String name;
	private String isbn;
	private Long authorId;

	public BookCreationRequest() {
		super();

	}

	public BookCreationRequest(String name, String isbn, Long authorId) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

}