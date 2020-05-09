package com.musicinfofinder.musicdetectorsrv.exceptions;

/**
 * Exception that should be thrown when AuthorizationService fails.
 */
public class AuthorizeException extends Exception {
	public AuthorizeException(String message, Exception exception) {
		super(message, exception);
	}

	public AuthorizeException(String message) {
		super(message);
	}
}
