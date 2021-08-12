package com.rahul.library.exceptions;

public class MemberAlreadyBorrowedBookException extends LibraryException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MemberAlreadyBorrowedBookException() {
		super("Member already have this book", GlobalErrorCode.ERROR_MEMBER_ALREADY_BORROWED);
	}

	public MemberAlreadyBorrowedBookException(String message, Long code) {
		super(message, code);
		
	}

}
