package com.musicinfofinder.musicdetectorsrv.exceptions;

public class InvalidParameterException extends GeneralMusicDetectorException {
	public InvalidParameterException(String s) {
		super(s);
	}

	public InvalidParameterException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
