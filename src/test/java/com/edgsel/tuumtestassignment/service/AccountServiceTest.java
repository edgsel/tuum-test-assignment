package com.edgsel.tuumtestassignment.service;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.converter.AccountConverter;
import com.edgsel.tuumtestassignment.exception.EntityNotFoundException;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.mappers.AccountMapper;
import com.edgsel.tuumtestassignment.mybatis.mappers.TransactionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO.EUR;
import static com.edgsel.tuumtestassignment.mybatis.enums.TransactionType.IN;
import static com.edgsel.tuumtestassignment.mybatis.enums.TransactionType.OUT;
import static com.edgsel.tuumtestassignment.testHelpers.TestHelper.getTestingAccount;
import static com.edgsel.tuumtestassignment.testHelpers.TestHelper.getTestingTransaction;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Account existingAccount = getTestingAccount(existingAccountId);

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

    @Test
    void shouldGetExistingAccountWithCalculatedBalances() {
        long existingAccountId = 1L;
        Account existingAccount = getTestingAccount(existingAccountId);

        List<BalanceDTO> expectedBalances = singletonList(
            BalanceDTO.builder()
                .currency(EUR)
                .amount(new BigDecimal("666.33"))
                .build()
        );

        AccountResponseDTO expected = new AccountResponseDTO(existingAccountId, "test-customer-id", expectedBalances);
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(getTestingTransaction(1L, existingAccountId, new BigDecimal("333.33"), IN));
        transactions.add(getTestingTransaction(2L, existingAccountId, new BigDecimal("333.00"), IN));
        transactions.add(getTestingTransaction(3L, existingAccountId, new BigDecimal("333.00"), IN));
        transactions.add(getTestingTransaction(4L, existingAccountId, new BigDecimal("333.00"), OUT));

        doReturn(existingAccount).when(accountMapper).findById(existingAccountId);
        doReturn(transactions).when(transactionMapper).getAllByAccountId(existingAccountId);
        doReturn(new AccountResponseDTO(existingAccountId, "test-customer-id", null))
            .when(accountConverter)
            .convertEntityToDto(existingAccount);

        AccountResponseDTO response = accountService.getAccount(existingAccountId);

        assertEquals(expected, response);
        assertEquals(expectedBalances.size(), 1);
        assertEquals(expectedBalances.get(0).getAmount(), BigDecimal.valueOf(666.33));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionIfAccountNotFound() {
        long accountId = 1L;
        doReturn(null).when(accountMapper).findById(accountId);

        assertThrows(
            EntityNotFoundException.class,
            () -> accountService.getAccount(accountId)
        );
    }
}
