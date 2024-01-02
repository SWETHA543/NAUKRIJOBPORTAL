package com.example.jobportal;

public class WorkExperienceNotFoundException extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WorkExperienceNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
