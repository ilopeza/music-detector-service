package com.musicinfofinder.musicdetectorsrv.exceptions;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

/**
 * Exception that should be thrown when AuthorizationService fails.
 */
public class AuthorizeException extends RuntimeException {
	HttpStatusCodeException httpStatusCodeException;

	public AuthorizeException(String s) {
		super(s);
	}

	public AuthorizeException(String s, HttpStatusCodeException httpStatusCodeException) {
		super(s);
		this.httpStatusCodeException = httpStatusCodeException;
	}

	public AuthorizeException(Throwable throwable) {
		super(throwable);
	}

	public HttpStatusCodeException getHttpStatusCodeException() {
		return httpStatusCodeException;
	}
}
