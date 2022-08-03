package com.edgsel.tuumtestassignment.controller;

import com.edgsel.tuumtestassignment.controller.dto.request.TransactionRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.TransactionResponseDTO;
import com.edgsel.tuumtestassignment.controller.validator.RequestValidator;
import com.edgsel.tuumtestassignment.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO.EUR;
import static com.edgsel.tuumtestassignment.controller.dto.enums.TransactionTypeDTO.IN;
import static com.edgsel.tuumtestassignment.testHelpers.TestHelper.getTestingTransactionRequest;
import static com.edgsel.tuumtestassignment.testHelpers.TestHelper.getTestingTransactionResponse;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.OK;

public class TransactionControllerTest {

    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Mock
    private RequestValidator requestValidator;

    @BeforeEach
    void setUp() {
        openMocks(this);
        transactionController = spy(new TransactionController(transactionService, requestValidator));
    }

    @Test
    void shouldCreateTransaction() {
        long transactionId = 2L;
        long accountId = 2L;

        TransactionRequestDTO transactionRequest = getTestingTransactionRequest(accountId, new BigDecimal("12345.22"), IN);
        TransactionResponseDTO expectedResponse = getTestingTransactionResponse(transactionId, 1L, new BigDecimal("12345.22"));

        expectedResponse.setBalances(singletonList(new BalanceDTO(EUR, new BigDecimal("12345.22"))));

        doReturn(2L).when(transactionService).saveTransaction(transactionRequest);
        doReturn(expectedResponse).when(transactionService).getTransaction(transactionId);

        ResponseEntity<TransactionResponseDTO> response = transactionController.createTransaction(transactionRequest);

        assertEquals(response.getStatusCode(), OK);
        assertEquals(expectedResponse.getId(), response.getBody().getId());
        assertEquals(expectedResponse.getAmount(), response.getBody().getAmount());
        assertEquals(expectedResponse.getAmount(), response.getBody().getAmount());
        assertEquals(expectedResponse.getBalances().size(), response.getBody().getBalances().size());
        assertEquals(expectedResponse.getBalances().get(0).getAmount(), response.getBody().getBalances().get(0).getAmount());
    }


    @Test
    void shouldGetTransactions() {
        long accountId = 1L;
        List<TransactionResponseDTO> expectedResponse = singletonList(getTestingTransactionResponse(2L, accountId, new BigDecimal("2.22")));

        doReturn(expectedResponse).when(transactionService).getTransactionsByAccountId(accountId);
        ResponseEntity<List<TransactionResponseDTO>> response = transactionController.getTransactions(accountId);

        assertEquals(response.getStatusCode(), OK);
        assertEquals(response.getBody().size(), 1);
    }
}
