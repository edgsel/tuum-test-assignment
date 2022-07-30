package com.edgsel.tuumtestassignment.service.account;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountDTO;
import com.edgsel.tuumtestassignment.dtoConverter.account.AccountConverter;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.mappers.AccountMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountConverter accountConverter;

    private final AccountMapper accountMapper;

    public AccountService(AccountConverter accountConverter, AccountMapper accountMapper) {
        this.accountConverter = accountConverter;
        this.accountMapper = accountMapper;
    }

    public void saveAccount(AccountDTO accountDTO) {
        Account account = accountConverter.convertDtoToEntity(accountDTO);

        accountMapper.insert(account);
    }

    public AccountDTO getAccount(String accountId) {
        Account account = accountMapper.findByAccountId(accountId);

        return accountConverter.entityToDto(account);
    }
}
