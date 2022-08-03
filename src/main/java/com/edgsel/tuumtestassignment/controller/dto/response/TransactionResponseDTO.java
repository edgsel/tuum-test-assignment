package com.edgsel.tuumtestassignment.controller.dto.response;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.controller.dto.enums.TransactionTypeDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponseDTO {

    @Schema(
        description = "Unique ID representing a transaction",
        example = "1",
        type = "long"
    )
    private long id;

    @Schema(
        description = "Unique ID representing a account",
        example = "1",
        type = "long"
    )
    private long accountId;

    @Schema(
        description = "Amount transferred with the transaction",
        example = "1.56",
        type = "double"
    )
    private BigDecimal amount;

    @Schema(
        description = "Currency used for transaction",
        example = "EUR",
        type = "enum"
    )
    private CurrencyDTO currency;

    @Schema(
        description = "Transaction type defining the direction of transaction (IN - increase balance, OUT - decrease balance)",
        example = "EUR",
        type = "enum"
    )
    private TransactionTypeDTO transactionType;

    @Schema(
        description = "Description (reason) of the transaction",
        example = "Lorum ipsum",
        type = "string"
    )
    private String description;

    @Schema(
        description = "A list of currencies of the account and balances",
        type = "array"
    )
    private List<BalanceDTO> balances;
}
