package com.rahul.library.exceptions;

public class MemberAlreadyRegisteredException extends LibraryException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MemberAlreadyRegisteredException() {
		super("Member already registered",GlobalErrorCode.ERROR_MEMBER_ALREADY_REGISTERED);
	}

	public MemberAlreadyRegisteredException(String message, Long code) {
		super(message, code);
	}

}
