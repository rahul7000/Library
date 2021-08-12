package com.rahul.library.requests;

public class BookLendRequest {

	private Long bookId;
	private Long memberId;

	public BookLendRequest() {
		super();

	}

	public BookLendRequest(Long bookId, Long memberId) {
		super();
		this.bookId = bookId;
		this.memberId = memberId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

}
