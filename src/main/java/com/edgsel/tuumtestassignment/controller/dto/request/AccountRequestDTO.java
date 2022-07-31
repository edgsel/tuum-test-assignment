package com.edgsel.tuumtestassignment.controller.dto.request;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequestDTO {

    @NotNull
    @NotEmpty(message = "Customer ID cannot be null or empty")
    private String customerId;

    @NotNull
    @NotEmpty(message = "Country cannot be null or empty")
    private String country;

    @NotEmpty(message = "At least one currency should be selected")
    private List<CurrencyDTO> currencies;
}
