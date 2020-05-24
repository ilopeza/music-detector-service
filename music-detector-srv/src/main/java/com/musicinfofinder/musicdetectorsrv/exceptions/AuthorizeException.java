package com.musicinfofinder.musicdetectorsrv.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception that should be thrown when AuthorizationService fails.
 */
public class AuthorizeException extends GeneralMusicDetectorException {

    public AuthorizeException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public AuthorizeException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }

    public AuthorizeException(String s) {
        super(s);
    }

    public AuthorizeException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
