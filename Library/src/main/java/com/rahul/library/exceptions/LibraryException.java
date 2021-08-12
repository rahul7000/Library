package com.rahul.library.exceptions;

public class LibraryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long code;

	public LibraryException(String message, Long code) {
		super(message);
		this.code = code;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}
	
	

}
