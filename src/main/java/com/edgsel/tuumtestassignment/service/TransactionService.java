package com.edgsel.tuumtestassignment.service;

import com.edgsel.tuumtestassignment.controller.dto.request.TransactionRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.TransactionResponseDTO;
import com.edgsel.tuumtestassignment.converter.TransactionConverter;
import com.edgsel.tuumtestassignment.exception.EntityNotFoundException;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.mappers.AccountMapper;
import com.edgsel.tuumtestassignment.mybatis.mappers.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.edgsel.tuumtestassignment.util.BalanceUtil.calculateBalances;
import static com.edgsel.tuumtestassignment.util.BalanceUtil.mapBalancesToDto;
import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
@Slf4j
public class TransactionService {

    private final TransactionConverter transactionConverter;

    private final TransactionMapper transactionMapper;

    private final AccountMapper accountMapper;

    public TransactionService(
        TransactionConverter transactionConverter,
        TransactionMapper transactionMapper,
        AccountMapper accountMapper
    ) {
        this.transactionConverter = transactionConverter;
        this.transactionMapper = transactionMapper;
        this.accountMapper = accountMapper;
    }

    public long saveTransaction(TransactionRequestDTO transactionRequest) {
        Account existingAccount = accountMapper.findById(transactionRequest.getAccountId());

        if (existingAccount != null) {
            Transaction transaction = transactionConverter.convertDtoToEntity(transactionRequest);
            List<Transaction> existingTransactions = transactionMapper.getAllByAccountId(existingAccount.getId());

            // add incoming transaction to all existing transactions for balance validation
            // and if all good, only then insert
            existingTransactions.add(transaction);
            calculateBalances(existingTransactions);

            transactionMapper.insert(transaction);

            log.info("Transaction with ID {} saved into database", transaction.getId());
            return transaction.getId();
        }

        throw new EntityNotFoundException("Account with ID " + transactionRequest.getAccountId() + " not found");
    }

    public TransactionResponseDTO getTransaction(long transactionId) {
        Transaction existingTransaction = transactionMapper.getByTransactionId(transactionId);

        if (existingTransaction != null) {
            log.info("Transaction with ID {} found", transactionId);

            List<Transaction> existingTransactions = transactionMapper.getAllByAccountId(existingTransaction.getAccountId());
            Map<String, BigDecimal> balances = calculateBalances(existingTransactions);
            TransactionResponseDTO response = transactionConverter.convertEntityToDto(existingTransaction);

            response.setBalances(mapBalancesToDto(balances));
            return response;
        }

        throw new EntityNotFoundException("Transaction with ID " + transactionId + " not found");
    }

    public List<TransactionResponseDTO> getTransactionsByAccountId(long accountId) {
        Account existingAccount = accountMapper.findById(accountId);

        if (existingAccount != null) {
            log.info("Account with ID {} found", existingAccount.getId());
            List<Transaction> transactions = transactionMapper.getAllByAccountId(accountId);

            if (isNotEmpty(transactions)) {
                log.info("Found {} transactions", transactions.size());
                return transactionConverter.convertAll(transactions);
            }

            log.info("No transactions found for account ID {}", accountId);
            return emptyList();
        }

        throw new EntityNotFoundException("Account with ID " + accountId + " not found");
    }
}
