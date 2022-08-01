package com.edgsel.tuumtestassignment.controller;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.validator.RequestValidator;
import com.edgsel.tuumtestassignment.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
@Slf4j
public class AccountController {

    private final AccountService accountService;

    private final RequestValidator requestValidator;

    public AccountController(AccountService accountService, RequestValidator accountValidator) {
        this.accountService = accountService;
        this.requestValidator = accountValidator;
    }

    @Operation(summary = "Create banking account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Banking account created",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = AccountResponseDTO.class))}),
        @ApiResponse(responseCode = "400", description = "Request body validation failed",
            content = @Content)
    })
    @RequestMapping(value = "/account", method = POST)
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequest) {
        log.info("Create account method called");
        requestValidator.validateAccount(accountRequest);

        long accountId = accountService.saveAccount(accountRequest);
        AccountResponseDTO response = accountService.getAccount(accountId);

        return new ResponseEntity<>(response, OK);
    }

    @Operation(summary = "Find banking account by account ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Banking account found",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = AccountResponseDTO.class))}),
        @ApiResponse(responseCode = "404", description = "Banking account not found",
            content = @Content)
    })
    @RequestMapping(value = "/account/{accountId}", method = GET)
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable long accountId) {
        log.info("Get account method called");
        AccountResponseDTO response = accountService.getAccount(accountId);

        return new ResponseEntity<>(response, OK);
    }
}
