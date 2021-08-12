package com.rahul.library.exceptions;

public class EntityNotFoundException extends LibraryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(){
        super("Entity Not Found", GlobalErrorCode.ERROR_ENTITY_NOT_FOUND);
    }

	public EntityNotFoundException(String message, Long code) {
		super(message, code);
	}

}
