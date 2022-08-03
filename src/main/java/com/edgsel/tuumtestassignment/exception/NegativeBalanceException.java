package com.edgsel.tuumtestassignment.exception;

public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException(String message) {
        super(message);
    }
}
