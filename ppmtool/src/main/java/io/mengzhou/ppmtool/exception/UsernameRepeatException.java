package io.mengzhou.ppmtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameRepeatException extends RuntimeException {
    public UsernameRepeatException(String message) {
        super(message);
    }
}
