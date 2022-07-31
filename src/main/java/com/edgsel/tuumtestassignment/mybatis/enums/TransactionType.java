package com.edgsel.tuumtestassignment.mybatis.enums;

import com.edgsel.tuumtestassignment.helper.EnumHelper;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionType {
    IN,
    OUT;

    @JsonCreator
    public static TransactionType create(String value) {
        return EnumHelper.create(TransactionType.class, value);
    }
}
