package com.edgsel.tuumtestassignment.testHelpers;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.controller.dto.enums.TransactionTypeDTO;
import com.edgsel.tuumtestassignment.controller.dto.request.TransactionRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.TransactionResponseDTO;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.enums.Currency;
import com.edgsel.tuumtestassignment.mybatis.enums.TransactionType;

import java.math.BigDecimal;

import static com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO.EUR;
import static com.edgsel.tuumtestassignment.controller.dto.enums.TransactionTypeDTO.IN;
import static java.util.Collections.singletonList;

public class TestHelper {

    public static Account getTestingAccount(long id) {
        return Account.builder()
            .id(id)
            .customerId("test-customer-id")
            .country("EST")
            .currencies(singletonList("EUR"))
            .build();
    }

    public static Transaction getTestingTransaction(long id, long accountId, BigDecimal amount, TransactionType type) {

        return Transaction.builder()
            .id(id)
            .accountId(accountId)
            .amount(amount)
            .currency(Currency.EUR)
            .transactionType(type)
            .description("test-description")
            .build();
    }

    public static TransactionRequestDTO getTestingTransactionRequest(long accountId, BigDecimal amount, TransactionTypeDTO type) {
        return new TransactionRequestDTO(
            accountId,
            amount,
            EUR,
            type,
            "test description"
        );
    }

    public static TransactionResponseDTO getTestingTransactionResponse(long id, long accountId, BigDecimal amount) {
       return new TransactionResponseDTO(
           id,
           accountId,
           amount,
           CurrencyDTO.EUR,
           IN,
           "test description",
           null
       );
    }
}
