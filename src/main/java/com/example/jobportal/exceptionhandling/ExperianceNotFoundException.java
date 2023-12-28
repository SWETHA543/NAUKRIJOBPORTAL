package com.example.jobportal.exceptionhandling;

public class ExperianceNotFoundException  extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExperianceNotFoundException(String message) {
		super();
		this.message = message;
	}
	

}
