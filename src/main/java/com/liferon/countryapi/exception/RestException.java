package com.liferon.countryapi.exception;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class RestException extends Exception {
    private static final long serialVersionUID = 1L;
    private Errors errors;
    private String error;
    private String error_description;

    public RestException(Errors err) {
        this.errors = err;
        unbundleError();
    }

    public RestException(String errorMessage) {
        this.error_description = errorMessage;
    }

    public RestException(String error, String description) {
        this.error = error;
        this.error_description = description;
    }

    private void unbundleError() {
        FieldError err = (FieldError) this.errors.getAllErrors().get(0);
        this.error = err.getCode();
        this.error_description = err.getDefaultMessage();
    }

    public String getError() {
        return this.error;
    }

    public String getError_description() {
        return this.error_description;
    }
}
