package com.edgsel.tuumtestassignment.controller.dto.response;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.controller.dto.enums.TransactionTypeDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private long id;

    private long accountId;

    private BigDecimal amount;

    private CurrencyDTO currency;

    private TransactionTypeDTO transactionType;

    private String description;

    private List<BalanceDTO> balances;
}
