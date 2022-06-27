package com.sigabem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotException extends Exception{
    private static final Long serialVersionUID = 1L;

    public ResourceNotException(String message ) {
        super(message);

    }

}
