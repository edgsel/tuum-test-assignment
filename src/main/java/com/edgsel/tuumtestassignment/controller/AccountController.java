package com.edgsel.tuumtestassignment.controller;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.validator.AccountValidator;
import com.edgsel.tuumtestassignment.service.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class AccountController {

    private final AccountService accountService;

    private final AccountValidator accountValidator;

    public AccountController(AccountService accountService, AccountValidator accountValidator) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
    }

    @RequestMapping(value = "/account", method = POST)
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequest) {
        accountValidator.validate(accountRequest);

        long accountId = accountService.saveAccount(accountRequest);

        AccountResponseDTO response = accountService.getAccount(accountId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/account/{accountId}", method = GET)
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable long accountId) {
        AccountResponseDTO response = accountService.getAccount(accountId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
