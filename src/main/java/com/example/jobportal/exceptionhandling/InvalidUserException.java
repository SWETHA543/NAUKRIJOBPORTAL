package com.example.jobportal.exceptionhandling;

public class InvalidUserException extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InvalidUserException(String message) {
		super();
		this.message = message;
	}
	

}
