package com.example.jobportal.exceptionhandling;

public class ProjectNotFoundException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProjectNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	

}
