package com.edgsel.tuumtestassignment.controller.dto.response;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
public class BalanceDTO {

    private CurrencyDTO currency;

    private BigDecimal amount;
}