package com.example.jobportal.exceptionhandling;

public class ResumeNotFoundException extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResumeNotFoundException(String message) {
		super();
		this.message = message;
	}

}
