package com.edgsel.tuumtestassignment.controller;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.controller.validator.RequestValidator;
import com.edgsel.tuumtestassignment.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO.EUR;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.OK;

public class AccountControllerTest {

    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Mock
    private RequestValidator requestValidator;

    @BeforeEach
    void setUp() {
        openMocks(this);
        accountController = spy(new AccountController(accountService, requestValidator));
    }

    @Test
    void createAccountTest() {
        AccountRequestDTO accountRequest = new AccountRequestDTO("test-customer-id", "EST", singletonList(EUR));
        List<BalanceDTO> balances = singletonList(
            BalanceDTO.builder()
                .currency(EUR)
                .amount(new BigDecimal("0.00"))
                .build());
        AccountResponseDTO account = new AccountResponseDTO(1L, "test-customer-id", balances);

        doReturn(1L).when(accountService).saveAccount(accountRequest);
        doReturn(account).when(accountService).getAccount(1L);

        ResponseEntity<AccountResponseDTO> response = accountController.createAccount(accountRequest);

        assertEquals(response.getStatusCode(), OK);
        assertEquals(account.getId(), response.getBody().getId());
        assertEquals(account.getCustomerId(), response.getBody().getCustomerId());
        assertEquals(account.getBalances().size(), response.getBody().getBalances().size());
    }

    @Test
    void getAccountTest() {
        long accountId = 1L;
        List<BalanceDTO> balances = singletonList(
            BalanceDTO.builder()
                .currency(EUR)
                .amount(new BigDecimal("0.00"))
                .build());
        AccountResponseDTO account = new AccountResponseDTO(1L, "test-customer-id", balances);

        doReturn(account).when(accountService).getAccount(accountId);

        ResponseEntity<AccountResponseDTO> response = accountController.getAccount(1L);

        assertEquals(response.getStatusCode(), OK);
        assertEquals(account.getId(), response.getBody().getId());
        assertEquals(account.getCustomerId(), response.getBody().getCustomerId());
        assertEquals(account.getBalances().size(), response.getBody().getBalances().size());
    }
}
