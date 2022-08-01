package com.edgsel.tuumtestassignment.converter;

import com.edgsel.tuumtestassignment.controller.dto.request.TransactionRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.TransactionResponseDTO;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {

    private final ModelMapper modelMapper;

    public TransactionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Transaction convertDtoToEntity(TransactionRequestDTO transactionRequestDTO) {
        return modelMapper.map(transactionRequestDTO, Transaction.class);
    }

    public TransactionResponseDTO convertEntityToDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionResponseDTO.class);
    }

    public List<TransactionResponseDTO> convertAll(List<Transaction> transactions) {
        return transactions.stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }
}
