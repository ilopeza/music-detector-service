package com.musicinfofinder.musicdetectorsrv.exceptions;

import org.springframework.http.HttpStatus;

public class SpotifyWebAPIException extends RuntimeException {
	HttpStatus httpStatus;

	public SpotifyWebAPIException(HttpStatus httpStatus, String error) {
		super(error);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
