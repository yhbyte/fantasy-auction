package com.bytes.and.dragons.fantasyauction.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.bytes.and.dragons.fantasyauction.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchAnyException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(exception.getLocalizedMessage());
        errorResponse.setDescription("Internal Server Error");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> catchAnyException(IllegalArgumentException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(BAD_REQUEST.value());
        errorResponse.setMessage(exception.getLocalizedMessage());
        errorResponse.setDescription("Bad Request");

        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

}
