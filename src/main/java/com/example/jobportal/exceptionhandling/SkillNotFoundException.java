package com.example.jobportal.exceptionhandling;

public class SkillNotFoundException extends RuntimeException {
private String message;

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public SkillNotFoundException(String message) {
	super();
	this.message = message;
}


}
