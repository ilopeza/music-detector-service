package com.musicinfofinder.musicdetectorsrv.exceptions;

public class AuthorizeException extends Exception {
	public AuthorizeException(String message, Exception exception) {
		super(message, exception);
	}

	public AuthorizeException(String message) {
		super(message);
	}
}
