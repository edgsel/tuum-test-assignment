package com.edgsel.tuumtestassignment.controller.dto.request;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Request object for creating banking account")
public class AccountRequestDTO {

    @NotNull(message = "Customer ID cannot be null")
    @NotEmpty(message = "Customer ID cannot be null or empty")
    @Schema(
        description = "Unique ID representing a customer",
        example = "1234-uuid-1234",
        type = "string",
        minLength = 1,
        required = true
    )
    private String customerId;

    @NotNull(message = "Country cannot be null")
    @NotEmpty(message = "Country cannot be empty")
    @Schema(
        description = "Country where the customer comes from",
        example = "EST",
        type = "string",
        minLength = 1,
        required = true
    )
    private String country;

    @NotNull(message = "Currencies cannot be null")
    @NotEmpty(message = "At least one currency should be selected")
    @Schema(
        description = "A list of currencies the account will use",
        type = "array",
        required = true
    )
    private List<CurrencyDTO> currencies;
}
