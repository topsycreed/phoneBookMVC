package com.pluralsight.phoneBookMVC.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object resourId) {
        super(resourId != null? String.format("Resource not found: %s", resourId.toString()) : null);
    }
}
