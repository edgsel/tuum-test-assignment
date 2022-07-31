package com.edgsel.tuumtestassignment.mybatis.enums;

import com.edgsel.tuumtestassignment.helper.EnumHelper;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Currency {
    EUR,
    SEK,
    USD,
    GBP;

    @JsonCreator
    public static Currency create(String value) {
        return EnumHelper.create(Currency.class, value);
    }
}
