package com.edgsel.tuumtestassignment.mybatis.mappers;

import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.enums.Currency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Before
    public void insertAccount() {
        Account account = Account.builder()
            .accountId("test-account-id")
            .customerId("test-customer-uuid")
            .country("EST")
            .currencies(Collections.singletonList(Currency.EUR))
            .build();

        accountMapper.insert(account);
    }

    @Test
    public void findByAccountIdTest() {
        Account account = accountMapper.findByAccountId("test-account-id");

        assertThat(account.getAccountId()).isEqualTo("test-account-id");
        assertThat(account.getCustomerId()).isEqualTo("test-customer-uuid");
        assertThat(account.getCountry()).isEqualTo("EST");
        assertNotNull(account.getCurrencies());
    }
}
