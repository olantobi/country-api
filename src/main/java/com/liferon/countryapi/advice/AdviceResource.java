package com.liferon.countryapi.advice;

import com.liferon.countryapi.exception.InvalidRequestParameterException;
import com.liferon.countryapi.exception.RestErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceResource {
    @ExceptionHandler(InvalidRequestParameterException.class)
    public ResponseEntity<RestErrorResponse> invalidRequestParameter(InvalidRequestParameterException ex) {
        RestErrorResponse model = new RestErrorResponse(ex.getError(),
                ex.getError_description());

        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<RestErrorResponse> accessDenied(AccessDeniedException ex) {
//        RestErrorResponse model = new RestErrorResponse(ex.getMessage(),
//                ex.getMessage());
//
//        return new ResponseEntity<>(model, HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(InternalServerErrorException.class)
//    public ResponseEntity<RestErrorResponse> internalServerError(InternalServerErrorException ex) {
//        RestErrorResponse model = new RestErrorResponse(ex.getError(),
//                ex.getError_description());
//
//        return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(ConflictException.class)
//    public ResponseEntity<RestErrorResponse> conflictException(ConflictException ex) {
//        RestErrorResponse model = new RestErrorResponse(ex.getError(),
//                ex.getError_description());
//
//        return new ResponseEntity<>(model, HttpStatus.CONFLICT);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorResponse> generalExceptionCatcher(Exception ex) {
        RestErrorResponse model = new RestErrorResponse(ex.getLocalizedMessage(),
                ex.getMessage());

        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }
}
