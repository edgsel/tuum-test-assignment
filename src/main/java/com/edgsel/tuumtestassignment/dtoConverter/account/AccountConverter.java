package com.edgsel.tuumtestassignment.dtoConverter.account;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountDTO;
import com.edgsel.tuumtestassignment.mybatis.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    private final ModelMapper modelMapper;

    public AccountConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Account convertDtoToEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }

    public AccountDTO entityToDto(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }
}
