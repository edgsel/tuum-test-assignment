package com.edgsel.tuumtestassignment.controller;

import com.edgsel.tuumtestassignment.controller.dto.request.TransactionRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.ErrorResponseDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.TransactionResponseDTO;
import com.edgsel.tuumtestassignment.controller.validator.RequestValidator;
import com.edgsel.tuumtestassignment.service.TransactionService;
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

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    private final RequestValidator requestValidator;

    public TransactionController(TransactionService transactionService, RequestValidator requestValidator) {
        this.transactionService = transactionService;
        this.requestValidator = requestValidator;
    }

    @Operation(summary = "Create transaction")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transaction created",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = TransactionResponseDTO.class))}),
        @ApiResponse(responseCode = "400", description = "Request body validation failed",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDTO.class))}),
        @ApiResponse(responseCode = "404", description = "Account ID not found",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDTO.class))})
    })
    @RequestMapping(value = "/transaction", method = POST)
    public ResponseEntity<TransactionResponseDTO> createTransaction(
        @RequestBody TransactionRequestDTO transactionRequest
    ) {
        log.info("Create transaction method called");
        requestValidator.validateTransaction(transactionRequest);

        long transactionId = transactionService.saveTransaction(transactionRequest);
        TransactionResponseDTO response = transactionService.getTransaction(transactionId);

        return new ResponseEntity<>(response, OK);
    }

    @Operation(summary = "Get transactions by account ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transactions found",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = TransactionResponseDTO.class))}),

        @ApiResponse(responseCode = "404", description = "Account ID not found",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDTO.class))})
    })
    @RequestMapping(value = "/transactions/{accountId}", method = GET)
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions(@PathVariable long accountId) {
        log.info("Get transactions method called");
        List<TransactionResponseDTO> response = transactionService.getTransactionsByAccountId(accountId);

        return new ResponseEntity<>(response, OK);
    }
}
