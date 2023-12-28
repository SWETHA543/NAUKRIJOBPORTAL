package com.example.jobportal.exceptionhandling;

public class JobNotFoundById extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JobNotFoundById(String message) {
		super();
		this.message = message;
	}

}
