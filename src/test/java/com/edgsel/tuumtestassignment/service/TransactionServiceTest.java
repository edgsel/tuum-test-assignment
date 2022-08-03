package com.edgsel.tuumtestassignment.service;

import com.edgsel.tuumtestassignment.controller.dto.request.TransactionRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.TransactionResponseDTO;
import com.edgsel.tuumtestassignment.converter.TransactionConverter;
import com.edgsel.tuumtestassignment.exception.EntityNotFoundException;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.enums.TransactionType;
import com.edgsel.tuumtestassignment.mybatis.mappers.AccountMapper;
import com.edgsel.tuumtestassignment.mybatis.mappers.TransactionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.edgsel.tuumtestassignment.controller.dto.enums.TransactionTypeDTO.IN;
import static com.edgsel.tuumtestassignment.testHelpers.TestHelper.getTestingAccount;
import static com.edgsel.tuumtestassignment.testHelpers.TestHelper.getTestingTransaction;
import static com.edgsel.tuumtestassignment.testHelpers.TestHelper.getTestingTransactionRequest;
import static com.edgsel.tuumtestassignment.testHelpers.TestHelper.getTestingTransactionResponse;
import static java.util.Collections.singletonList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.util.CollectionUtils.isEmpty;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @Mock
    private TransactionConverter transactionConverter;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private AccountMapper accountMapper;

    @BeforeEach
    void setUp() {
        openMocks(this);
        transactionService = spy(new TransactionService(transactionConverter, transactionMapper, accountMapper));
    }

    @Test
    void shouldSaveTransaction() {
        long existingAccountId = 2L;
        Account account = getTestingAccount(existingAccountId);
        TransactionRequestDTO transactionRequest = getTestingTransactionRequest(existingAccountId, new BigDecimal("1234.44"), IN);

        Transaction transaction = getTestingTransaction(1L, existingAccountId, new BigDecimal("1234.44"), TransactionType.IN);

        doReturn(account).when(accountMapper).findById(existingAccountId);
        doReturn(transaction).when(transactionConverter).convertDtoToEntity(transactionRequest);
        doReturn(new ArrayList<>()).when(transactionMapper).getAllByAccountId(existingAccountId);

        transactionService.saveTransaction(transactionRequest);

        verify(transactionMapper).insert(transaction);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionIfAccountNotFound_saveTransaction() {
        long accountId = 3L;
        TransactionRequestDTO transactionRequest = getTestingTransactionRequest(accountId, new BigDecimal("1234.44"), IN);

        doReturn(null).when(accountMapper).findById(accountId);

        assertThrows(
            EntityNotFoundException.class,
            () -> transactionService.saveTransaction(transactionRequest)
        );
    }

    @Test
    void shouldGetTransactionById() {
        long existingTransactionId = 1L;
        long existingAccountId = 12L;

        Transaction transaction = getTestingTransaction(
            existingTransactionId,
            existingAccountId,
            new BigDecimal("1234.44"),
            TransactionType.IN
        );

        TransactionResponseDTO expectedResponse = getTestingTransactionResponse(existingTransactionId, existingAccountId, new BigDecimal("1234.44"));

        doReturn(transaction).when(transactionMapper).getByTransactionId(existingTransactionId);
        doReturn(singletonList(transaction)).when(transactionMapper).getAllByAccountId(existingAccountId);
        doReturn(expectedResponse).when(transactionConverter).convertEntityToDto(transaction);

        TransactionResponseDTO response = transactionService.getTransaction(existingTransactionId);

        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getBalances().get(0).getAmount(), response.getBalances().get(0).getAmount());
    }

    @Test
    void shouldThrowEntityNotFoundExceptionIfTransactionNotFound() {
        long transactionId = 1L;

        doReturn(null).when(transactionMapper).getByTransactionId(transactionId);

        assertThrows(
            EntityNotFoundException.class,
            () -> transactionService.getTransaction(transactionId)
        );
    }

    @Test
    void shouldReturnListOfTransactions() {
        long existingTransactionId = 1L;
        long existingAccountId = 2L;
        Account account = getTestingAccount(existingAccountId);

        Transaction transaction = getTestingTransaction(
            existingTransactionId,
            existingAccountId,
            new BigDecimal("1234.44"),
            TransactionType.IN
        );

        List<TransactionResponseDTO> expectedResponse = singletonList(
            getTestingTransactionResponse(existingTransactionId, existingAccountId, new BigDecimal("1234.44"))
        );

        List<Transaction> transactions = singletonList(transaction);

        doReturn(account).when(accountMapper).findById(existingAccountId);
        doReturn(transactions).when(transactionMapper).getAllByAccountId(existingAccountId);
        doReturn(expectedResponse).when(transactionConverter).convertAll(transactions);

        List<TransactionResponseDTO> response = transactionService.getTransactionsByAccountId(existingAccountId);

        assertTrue(isNotEmpty(response));
        assertEquals(expectedResponse.get(0).getAmount(), response.get(0).getAmount());
        assertNull(expectedResponse.get(0).getBalances());
    }

    @Test
    void shouldReturnEmptyListOfTransactions() {
        long existingAccountId = 2L;
        Account account = getTestingAccount(existingAccountId);

        doReturn(account).when(accountMapper).findById(existingAccountId);
        doReturn(null).when(transactionMapper).getAllByAccountId(existingAccountId);

        List<TransactionResponseDTO> response = transactionService.getTransactionsByAccountId(existingAccountId);

        assertTrue(isEmpty(response));
    }
}
