package com.edgsel.tuumtestassignment.util;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.exception.NegativeBalanceException;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.enums.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.edgsel.tuumtestassignment.mybatis.enums.TransactionType.IN;
import static com.edgsel.tuumtestassignment.mybatis.enums.TransactionType.OUT;
import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

public class BalanceUtil {

    public static Map<String, BigDecimal> getInitialBalances(List<String> currencies) {
        return currencies.stream()
            .collect(Collectors.toMap(currency -> currency, currency -> new BigDecimal("0.00")));
    }

    public static Map<String, BigDecimal> calculateBalances(List<Transaction> transactions) {
        Map<String, BigDecimal> result = new HashMap<>();
        Map<String, List<Transaction>> transactionsGroupedByCurrency = transactions.stream()
            .collect(Collectors.groupingBy(transaction -> transaction.getCurrency().name()));

        // Calculate balance for each currency
        transactionsGroupedByCurrency.forEach((currency, value) -> {
            BigDecimal inSum = getTransactionsByType(value, IN)
                .stream()
                .map(Transaction::getAmount)
                .reduce(ZERO, BigDecimal::add);

            BigDecimal outSum = getTransactionsByType(value, OUT)
                .stream()
                .map(Transaction::getAmount)
                .reduce(ZERO, BigDecimal::subtract);

            BigDecimal totalBalance = inSum.add(outSum).setScale(2, HALF_UP);

            if (totalBalance.signum() < 0) {
                throw new NegativeBalanceException(
                    format("Transaction failed for currency %s, not enough funds", currency)
                );
            }

            result.put(currency, totalBalance);
        });

        return result;
    }

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

    private static List<Transaction> getTransactionsByType(List<Transaction> transactions, TransactionType type) {
        return transactions.stream()
            .filter(transaction -> transaction.getTransactionType() == type)
            .collect(Collectors.toList());
    }
}
