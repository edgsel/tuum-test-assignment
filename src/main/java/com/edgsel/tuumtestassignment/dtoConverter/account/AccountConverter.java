package com.edgsel.tuumtestassignment.dtoConverter.account;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.enums.Currency;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountConverter {

    private final ModelMapper modelMapper;

    public AccountConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Account convertDtoToEntity(AccountRequestDTO accountRequestDTO) {
        return modelMapper.map(accountRequestDTO, Account.class);
    }

    public AccountResponseDTO entityToDto(Account account) {
        System.out.println(account.getId());
        return modelMapper.map(account, AccountResponseDTO.class);
    }
}
