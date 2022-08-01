package com.edgsel.tuumtestassignment.controller.validator;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.request.TransactionRequestDTO;
import com.edgsel.tuumtestassignment.exception.RequestValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Component
public class RequestValidator {

    private final Validator validator;

    public RequestValidator(Validator validator) {
        this.validator = validator;
    }

    public void validateAccount(AccountRequestDTO accountRequest) {
        List<ConstraintViolation> violations = validator.validate(accountRequest);

        if (isNotEmpty(violations)) {
            String errors = buildErrorMessage(violations);

            throw new RequestValidationException(format("Request body validation failed errors: [%s]", errors));
        }
    }

    public void validateTransaction(TransactionRequestDTO transactionRequest) {
        List<ConstraintViolation> violations = validator.validate(transactionRequest);

        if (isNotEmpty(violations)) {
            String errors = buildErrorMessage(violations);

            throw new RequestValidationException(format("Request body validation failed errors: [%s]", errors));
        }
    }

    private String buildErrorMessage(List<ConstraintViolation> violations) {
        return violations.stream()
            .map(ConstraintViolation::getMessage)
            .map(s -> "'" + s + "'")
            .collect(Collectors.joining(", "));
    }
}
