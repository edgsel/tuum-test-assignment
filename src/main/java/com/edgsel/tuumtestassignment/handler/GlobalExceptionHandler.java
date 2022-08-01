package com.edgsel.tuumtestassignment.handler;

import com.edgsel.tuumtestassignment.controller.dto.response.ErrorResponseDTO;
import com.edgsel.tuumtestassignment.exception.EntityNotFoundException;
import com.edgsel.tuumtestassignment.exception.InvalidEnumValueException;
import com.edgsel.tuumtestassignment.exception.RequestValidationException;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundExceptions(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleRequestValidationExceptions(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage()), BAD_REQUEST);
    }
}
