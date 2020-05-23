package com.musicinfofinder.musicdetectorsrv.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GeneralMusicDetectorException {
    public UserNotFoundException(String s) {
        super(s);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
