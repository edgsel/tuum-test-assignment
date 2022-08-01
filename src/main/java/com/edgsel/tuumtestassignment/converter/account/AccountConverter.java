package com.edgsel.tuumtestassignment.converter.account;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.mybatis.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    private final ModelMapper modelMapper;

    public AccountConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Account convertDtoToEntity(AccountRequestDTO accountRequest) {
        return modelMapper.map(accountRequest, Account.class);
    }

    public AccountResponseDTO convertEntityToDto(Account account) {
        return modelMapper.map(account, AccountResponseDTO.class);
    }
}
