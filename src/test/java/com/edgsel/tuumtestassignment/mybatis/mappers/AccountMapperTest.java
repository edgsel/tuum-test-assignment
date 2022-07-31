package com.edgsel.tuumtestassignment.mybatis.mappers;

import com.edgsel.tuumtestassignment.mybatis.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    private long accountId;

    @BeforeEach
    void insertAccount() {
        Account account = Account.builder()
            .customerId("test-customer-uuid")
            .country("EST")
            .currencies(Collections.singletonList("EUR"))
            .build();

        accountMapper.insert(account);
        accountId = account.getId();
    }

    @Test
    void findByAccountIdTest() {
        Account account = accountMapper.findById(accountId);

        assertThat(account.getId()).isEqualTo(accountId);
        assertThat(account.getCustomerId()).isEqualTo("test-customer-uuid");
        assertThat(account.getCountry()).isEqualTo("EST");
        assertThat(account.getCurrencies()).isNotEmpty();
        assertNotNull(account.getCurrencies());
    }
}
