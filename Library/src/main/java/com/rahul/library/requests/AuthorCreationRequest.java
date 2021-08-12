package com.rahul.library.requests;

public class AuthorCreationRequest {

	private String firstName;
	private String lastName;

	public AuthorCreationRequest(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AuthorCreationRequest() {
		super();

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
