package com.musicinfofinder.musicdetectorsrv.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralMusicDetectorException extends RuntimeException {
	HttpStatus httpStatus;

	public GeneralMusicDetectorException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public GeneralMusicDetectorException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}

	public GeneralMusicDetectorException(String s) {
		super(s);
	}

	public GeneralMusicDetectorException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
