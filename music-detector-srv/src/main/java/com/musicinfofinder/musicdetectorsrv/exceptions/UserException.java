package com.musicinfofinder.musicdetectorsrv.exceptions;

public class UserException extends Exception {
	public UserException(String s) {
		super(s);
	}

	public UserException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
