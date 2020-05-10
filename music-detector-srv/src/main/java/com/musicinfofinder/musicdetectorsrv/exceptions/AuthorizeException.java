package com.musicinfofinder.musicdetectorsrv.exceptions;

import org.springframework.web.client.HttpStatusCodeException;

/**
 * Exception that should be thrown when AuthorizationService fails.
 */
public class AuthorizeException extends RuntimeException {
	HttpStatusCodeException httpStatusCodeException;

	public AuthorizeException(HttpStatusCodeException httpStatusCodeException) {
		this.httpStatusCodeException = httpStatusCodeException;
	}

	public AuthorizeException(Throwable throwable) {
		super(throwable);
	}

	public AuthorizeException(String message, Exception exception) {
		super(message, exception);
	}

	public AuthorizeException(String message) {
		super(message);
	}

	public HttpStatusCodeException getHttpStatusCodeException() {
		return httpStatusCodeException;
	}
}
