package com.musicinfofinder.musicdetectorsrv.exceptions;

public class GeneralMusicDetectorException extends RuntimeException {
	public GeneralMusicDetectorException(String s) {
		super(s);
	}

	public GeneralMusicDetectorException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
