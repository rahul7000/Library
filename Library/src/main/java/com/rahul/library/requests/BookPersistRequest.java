package com.rahul.library.requests;

import java.util.List;

import com.rahul.library.entities.Book;

public class BookPersistRequest {
	
	List<BookCreationRequest> books;

	/**
	 * @return the books
	 */
	public List<BookCreationRequest> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<BookCreationRequest> books) {
		this.books = books;
	}

}
