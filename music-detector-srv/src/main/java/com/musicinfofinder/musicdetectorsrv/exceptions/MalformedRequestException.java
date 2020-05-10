package com.musicinfofinder.musicdetectorsrv.exceptions;

/**
 * This exception should be thrown only when the request is not well formed.
 */
public class MalformedRequestException extends GeneralMusicDetectorException {
	public MalformedRequestException(String s) {
		super(s);
	}

	public MalformedRequestException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
