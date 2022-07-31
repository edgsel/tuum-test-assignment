package com.edgsel.tuumtestassignment.handler;

import com.edgsel.tuumtestassignment.controller.dto.response.ErrorResponseDTO;
import com.edgsel.tuumtestassignment.exception.AccountNotFoundException;
import com.edgsel.tuumtestassignment.exception.InvalidEnumValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEnumValueException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidEnumExceptions(InvalidEnumValueException ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundExceptions(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage()), NOT_FOUND);
    }
}
