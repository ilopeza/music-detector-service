package com.musicinfofinder.musicdetectorsrv.exceptions;

public class UserException extends GeneralMusicDetectorException {

    public UserException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UserException(String message) {
        super(message);
    }
}
