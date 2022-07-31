package com.edgsel.tuumtestassignment.controller.dto.enums;

import com.edgsel.tuumtestassignment.helper.EnumHelper;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum CurrencyDTO {
    EUR,
    SEK,
    USD,
    GBP;

    @JsonCreator
    public static CurrencyDTO create(String value) {
        return EnumHelper.create(CurrencyDTO.class, value);
    }
}
