package com.liferon.countryapi.exception;

import org.springframework.validation.Errors;

public class InternalServerErrorException extends RestException {
    public InternalServerErrorException(Errors err) {
        super(err);
    }

    public InternalServerErrorException(String errorMessage) {
        super(errorMessage);
    }

    public InternalServerErrorException(String error, String description) {
        super(error, description);
    }
}
