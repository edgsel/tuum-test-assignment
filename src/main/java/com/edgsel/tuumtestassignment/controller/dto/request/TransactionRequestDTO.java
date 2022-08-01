package com.edgsel.tuumtestassignment.controller.dto.request;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.controller.dto.enums.TransactionTypeDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private long accountId;

    @NotNull(message = "Amount cannot be null")
    @NotEmpty(message = "Amount cannot be empty")
    @Min(message= "Amount cannot be negative", value = 1)
    private BigDecimal amount;

    @NotNull(message = "Currency cannot be null")
    @NotEmpty(message = "Currency cannot be empty")
    private CurrencyDTO currency;

    @NotNull(message = "Transaction type cannot be null")
    @NotEmpty(message = "Transaction type cannot be empty")
    private TransactionTypeDTO transactionType;

    @NotNull(message = "Transaction type cannot be null")
    @NotEmpty(message = "Transaction type cannot be empty")
    private String description;
}
