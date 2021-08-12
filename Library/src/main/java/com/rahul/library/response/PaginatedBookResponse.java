package com.rahul.library.response;

import java.util.List;

import com.rahul.library.entities.Book;

public class PaginatedBookResponse {

	private List<Book> bookList;
	private Long numberOfItems;
	private int numberOfPages;

	public PaginatedBookResponse() {
		super();
	}

	public PaginatedBookResponse(List<Book> bookList, Long numberOfItems, int numberOfPages) {
		super();
		this.bookList = bookList;
		this.numberOfItems = numberOfItems;
		this.numberOfPages = numberOfPages;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public Long getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Long numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

}
