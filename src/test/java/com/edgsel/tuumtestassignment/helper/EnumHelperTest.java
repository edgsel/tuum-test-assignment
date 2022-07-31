package com.edgsel.tuumtestassignment.helper;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.exception.InvalidEnumValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnumHelperTest {

    @Test
    void shouldCreateCorrectEnum() {
        assertNull(EnumHelper.create(CurrencyDTO.class, null));
        assertEquals(CurrencyDTO.EUR, EnumHelper.create(CurrencyDTO.class, "EUR"));
    }

    @Test
    void shouldThrowExceptionIfValueNotFound() {
        InvalidEnumValueException ex = assertThrows(
            InvalidEnumValueException.class,
            () -> EnumHelper.create(CurrencyDTO.class, "JPY")
        );

        assertEquals(
            "Invalid value JPY was provided for for class CurrencyDTO. Supported values: [EUR, SEK, USD, GBP]",
            ex.getMessage()
        );
    }
}
