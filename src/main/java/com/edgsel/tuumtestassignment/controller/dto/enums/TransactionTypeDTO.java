package com.edgsel.tuumtestassignment.controller.dto.enums;

import com.edgsel.tuumtestassignment.helper.EnumHelper;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionTypeDTO {
    IN,
    OUT;

    @JsonCreator
    public static TransactionTypeDTO create(String value) {
        return EnumHelper.create(TransactionTypeDTO.class, value);
    }
}
