package com.rahul.library.exceptions;

import java.util.Date;

public class ExceptionResponse {

	private Date timeStamp;
	private String message;
	private Long code;

	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(String message, Long code) {
		super();
		this.timeStamp = new Date();
		this.message = message;
		this.code = code;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

}
