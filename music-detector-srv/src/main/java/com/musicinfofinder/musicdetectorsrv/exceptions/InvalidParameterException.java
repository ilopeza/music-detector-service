package com.musicinfofinder.musicdetectorsrv.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends GeneralMusicDetectorException {
	public InvalidParameterException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

	public InvalidParameterException(String s) {
		super(s);
	}

}
