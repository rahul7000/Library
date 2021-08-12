package com.rahul.library.requests;

public class MemberCreationRequest {

	private String firstName;
	private String lastName;

	public MemberCreationRequest() {
		super();

	}

	public MemberCreationRequest(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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
