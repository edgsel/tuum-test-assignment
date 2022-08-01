package com.edgsel.tuumtestassignment.controller.validator;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.exception.RequestValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Component
public class AccountValidator {

    private final Validator validator;

    public AccountValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(AccountRequestDTO accountRequest) {
        List<ConstraintViolation> violations = validator.validate(accountRequest);

        if (isNotEmpty(violations)) {
            String errors = violations.stream()
                .map(ConstraintViolation::getMessage)
                .map(s -> "'" + s + "'")
                .collect(Collectors.joining(", "));

            throw new RequestValidationException(format("Request body validation failed errors: [%s]", errors));
        }
    }
}
