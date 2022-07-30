package com.edgsel.tuumtestassignment.mybatis.mappers;

import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.enums.Currency;
import com.edgsel.tuumtestassignment.mybatis.enums.TransactionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionMapperTest {

    @Autowired
    private TransactionMapper transactionMapper;

    private long transactionId;

    @Before
    public void insertTransaction() {
        Transaction transaction = Transaction.builder()
            .accountId(1L)
            .amount(new BigDecimal("6.66"))
            .currency(Currency.GBP)
            .transactionType(TransactionType.IN)
            .build();

        transactionMapper.insert(transaction);
        transactionId = transaction.getId();
    }

    @Test
    public void getByTransactionIdTest() {
        Transaction transaction = transactionMapper.getByTransactionId(transactionId);

        assertThat(transaction.getId()).isEqualTo(transactionId);
        assertThat(transaction.getAccountId()).isEqualTo(1L);
        assertThat(transaction.getAmount()).isEqualTo("6.66");
        assertThat(transaction.getCurrency().name()).isEqualTo("GBP");
        assertThat(transaction.getTransactionType().name()).isEqualTo("IN");
    }

    @Test
    public void getAllByAccountIdTest() {
        List<Transaction> allTransactionsByAccountId = transactionMapper.getAllByAccountId(1L);

        assertFalse(allTransactionsByAccountId.isEmpty());
    }
}
