package com.stackroute.favouriteservice.exception;

public class PlayerNotFoundException extends Exception {

	
	
	private String message;
	public PlayerNotFoundException(String message) {
		super(message);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
