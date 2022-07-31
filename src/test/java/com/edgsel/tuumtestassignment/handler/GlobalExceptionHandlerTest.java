package com.edgsel.tuumtestassignment.handler;

import com.edgsel.tuumtestassignment.controller.dto.response.ErrorResponseDTO;
import com.edgsel.tuumtestassignment.exception.AccountNotFoundException;
import com.edgsel.tuumtestassignment.exception.InvalidEnumValueException;
import com.edgsel.tuumtestassignment.exception.RequestValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        openMocks(this);
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleInvalidEnumExceptions_returns400() {
        InvalidEnumValueException exception = new InvalidEnumValueException("test message");
        ResponseEntity<ErrorResponseDTO> response = handler.handleInvalidEnumExceptions(exception);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertSame("test message", response.getBody().getMessage());
    }

    @Test
    void handleRequestValidationExceptions_returns400() {
        RequestValidationException exception = new RequestValidationException("test message");
        ResponseEntity<ErrorResponseDTO> response = handler.handleRequestValidationExceptions(exception);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertSame("test message", response.getBody().getMessage());
    }

    @Test
    void handleNotFoundExceptions_return404() {
        AccountNotFoundException exception = new AccountNotFoundException("test message");
        ResponseEntity<ErrorResponseDTO> response = handler.handleNotFoundExceptions(exception);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertSame("test message", response.getBody().getMessage());
    }
}
