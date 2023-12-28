package com.example.jobportal.exceptionhandling;

public class ProjectNotFoundByIdException  extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProjectNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	

}
