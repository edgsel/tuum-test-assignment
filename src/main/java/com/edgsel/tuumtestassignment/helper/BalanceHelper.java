package com.edgsel.tuumtestassignment.helper;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceHelper {

    public static List<BalanceDTO> getBalances(Map<String, BigDecimal> balancesAndCurrencies) {
        List<BalanceDTO> balances = new ArrayList<>();

        balancesAndCurrencies.forEach((key, value) -> {
            CurrencyDTO castedCurrency = CurrencyDTO.valueOf(key);
            BalanceDTO balance = BalanceDTO.builder()
                .currency(castedCurrency)
                .amount(value)
                .build();

            balances.add(balance);
        });

        return balances;
    }
}
