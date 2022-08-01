package com.edgsel.tuumtestassignment.service.transaction;

import com.edgsel.tuumtestassignment.controller.dto.request.TransactionRequestDTO;
import com.edgsel.tuumtestassignment.converter.transaction.TransactionConverter;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.mappers.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionService {

    private final TransactionConverter transactionConverter;

    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionConverter transactionConverter, TransactionMapper transactionMapper) {
        this.transactionConverter = transactionConverter;
        this.transactionMapper = transactionMapper;
    }

    public long saveTransaction(TransactionRequestDTO transactionRequest) {
        Transaction transaction = transactionConverter.convertDtoToEntity(transactionRequest);

        transactionMapper.insert(transaction);

        log.info("Transaction with ID {} saved into database", transaction.getId());
        return transaction.getId();
    }
}
