package com.edgsel.tuumtestassignment.testHelpers;

import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.enums.Currency;
import com.edgsel.tuumtestassignment.mybatis.enums.TransactionType;

import java.math.BigDecimal;

public class TransactionsTestHelper {

    public static Transaction getTestingTransaction(long id, long accountId, BigDecimal amount, TransactionType type) {

        return Transaction.builder()
            .id(id)
            .accountId(accountId)
            .amount(amount)
            .currency(Currency.EUR)
            .transactionType(type)
            .build();
    }
}
