package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.DocumentNumberIncorrectException;
import com.pragma.powerup.domain.exception.EmailAlreadyExistsException;
import com.pragma.powerup.domain.exception.EmailIncorrectException;
import com.pragma.powerup.domain.exception.PnoneIncorrectException;
import com.pragma.powerup.infrastructure.exception.DomainException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> DomainException(
            DomainException DomainExceptionException) {

        if (DomainExceptionException instanceof PnoneIncorrectException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.
                    singletonMap(MESSAGE, ExceptionResponse.PHONE_INCORRECT.getMessage()));
        }
        else if (DomainExceptionException instanceof EmailAlreadyExistsException){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMAIL_EXIST.getMessage()));
        }else if (DomainExceptionException instanceof EmailIncorrectException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMAIL_INCORRECT.getMessage()));
        }
        else if (DomainExceptionException instanceof DocumentNumberIncorrectException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.DOCUMENT_NUMBER_INCORRECT.getMessage()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
        }



    }



    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(
            BadCredentialsException badCredentialsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap("BAD CREDENTIALS", "BAD CREDENTIALS"));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }


    
}