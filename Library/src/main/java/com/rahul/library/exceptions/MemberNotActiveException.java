package com.rahul.library.exceptions;

public class MemberNotActiveException extends LibraryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemberNotActiveException() {
		super("Member Not Active",GlobalErrorCode.ERROR_MEMBER_ID_NOT_ACTIVE);
	}

	public MemberNotActiveException(String message, Long code) {
		super(message, code);
	}

}
