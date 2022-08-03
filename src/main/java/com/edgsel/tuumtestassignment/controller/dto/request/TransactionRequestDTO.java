package com.edgsel.tuumtestassignment.controller.dto.request;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.controller.dto.enums.TransactionTypeDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
public class TransactionRequestDTO {

    @NotNull(message = "Account ID cannot be null")
    @NotEmpty(message = "Account ID cannot be empty")
    @Schema(
        description = "Unique ID representing an account",
        example = "1",
        type = "long",
        required = true
    )
    private long accountId;

    @NotNull(message = "Amount cannot be null")
    @NotEmpty(message = "Amount cannot be empty")
    @Min(message= "Amount cannot be negative", value = 1)
    @Schema(
        description = "Amount to be transferred",
        example = "1.56",
        type = "double",
        required = true
    )
    private BigDecimal amount;

    @NotNull(message = "Currency cannot be null")
    @NotEmpty(message = "Currency cannot be empty")
    @Schema(
        description = "Currency used for transaction",
        example = "EUR",
        type = "enum",
        required = true
    )
    private CurrencyDTO currency;

    @NotNull(message = "Transaction type cannot be null")
    @NotEmpty(message = "Transaction type cannot be empty")
    @Schema(
        description = "Transaction type defining the direction of transaction (IN - increase balance, OUT - decrease balance)",
        example = "EUR",
        type = "enum",
        required = true
    )
    private TransactionTypeDTO transactionType;

    @NotNull(message = "Transaction type cannot be null")
    @NotEmpty(message = "Transaction type cannot be empty")
    @Schema(
        description = "Description (reason) of the transaction",
        example = "Lorum ipsum",
        type = "string",
        required = true
    )
    private String description;
}
