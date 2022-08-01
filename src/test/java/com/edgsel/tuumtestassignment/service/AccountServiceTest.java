package com.edgsel.tuumtestassignment.service;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.converter.AccountConverter;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.mappers.AccountMapper;
import com.edgsel.tuumtestassignment.mybatis.mappers.TransactionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import static com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO.EUR;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private AccountConverter accountConverter;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private TransactionMapper transactionMapper;

    @BeforeEach
    void setUp() {
        openMocks(this);
        accountService = spy(new AccountService(accountConverter, accountMapper, transactionMapper));
    }

    @Test
    void shouldSaveAccount() {
        Account account = mock(Account.class);
        AccountRequestDTO accountRequest = mock(AccountRequestDTO.class);

        doReturn(account).when(accountConverter).convertDtoToEntity(accountRequest);

        accountService.saveAccount(accountRequest);

        verify(accountMapper).insert(account);
    }

    @Test
    void shouldGetExistingAccountWithInitialBalances() {
        long existingAccountId = 1L;
        Account existingAccount = Account.builder()
            .id(1L)
            .customerId("test-customer-id")
            .country("EST")
            .currencies(singletonList("EUR"))
            .build();

        List<BalanceDTO> balances = singletonList(
            BalanceDTO.builder()
                .currency(EUR)
                .amount(new BigDecimal("0.00"))
                .build()
        );
        AccountResponseDTO expected = new AccountResponseDTO(existingAccountId, "test-customer-id", balances);

        doReturn(existingAccount).when(accountMapper).findById(existingAccountId);
        doReturn(emptyList()).when(transactionMapper).getAllByAccountId(existingAccountId);
        doReturn(new AccountResponseDTO(existingAccountId, "test-customer-id", null))
            .when(accountConverter)
            .convertEntityToDto(existingAccount);

        AccountResponseDTO response = accountService.getAccount(existingAccountId);

        assertEquals(expected, response);
        assertEquals(balances.size(), response.getBalances().size());

    }
}
