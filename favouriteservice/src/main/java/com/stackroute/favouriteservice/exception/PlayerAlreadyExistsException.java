package com.stackroute.favouriteservice.exception;

public class PlayerAlreadyExistsException extends Exception{

	private String message;
	
	public PlayerAlreadyExistsException(String message) {
		super(message);
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
