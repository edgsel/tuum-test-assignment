package com.edgsel.tuumtestassignment.mybatis.mappers;

import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.enums.Currency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    private long accountId;

    @Before
    @Transactional
    @Rollback
    public void insertAccount() {
        Account account = Account.builder()
            .customerId("test-customer-uuid")
            .country("EST")
            .currencies(Collections.singletonList("EUR"))
            .build();

        accountMapper.insert(account);
        accountId = account.getId();
    }

    @Test
    public void findByAccountIdTest() {
        Account account = accountMapper.findById(accountId);

        assertThat(account.getId()).isEqualTo(accountId);
        assertThat(account.getCustomerId()).isEqualTo("test-customer-uuid");
        assertThat(account.getCountry()).isEqualTo("EST");
        assertThat(account.getCurrencies()).isNotEmpty();
        assertNotNull(account.getCurrencies());
    }
}
