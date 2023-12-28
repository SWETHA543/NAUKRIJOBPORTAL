package com.example.jobportal.exceptionhandling;

public class UserNotFounfByThisId extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserNotFounfByThisId(String message) {
		super();
		this.message = message;
	}

}
