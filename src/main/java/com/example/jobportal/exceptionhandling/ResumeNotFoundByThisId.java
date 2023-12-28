package com.example.jobportal.exceptionhandling;

public class ResumeNotFoundByThisId extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResumeNotFoundByThisId(String message) {
		super();
		this.message = message;
	}
	


}
