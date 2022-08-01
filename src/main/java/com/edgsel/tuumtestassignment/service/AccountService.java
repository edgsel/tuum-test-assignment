package com.edgsel.tuumtestassignment.service;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.converter.AccountConverter;
import com.edgsel.tuumtestassignment.exception.AccountNotFoundException;
import com.edgsel.tuumtestassignment.helper.BalanceHelper;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.mappers.AccountMapper;
import com.edgsel.tuumtestassignment.mybatis.mappers.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Slf4j
public class AccountService {

    private final AccountConverter accountConverter;

    private final AccountMapper accountMapper;

    private final TransactionMapper transactionMapper;

    public AccountService(
        AccountConverter accountConverter,
        AccountMapper accountMapper,
        TransactionMapper transactionMapper
    ) {
        this.accountConverter = accountConverter;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    public long saveAccount(AccountRequestDTO accountRequest) {
        Account account = accountConverter.convertDtoToEntity(accountRequest);

        accountMapper.insert(account);

        log.info("Account with ID {} saved into database", account.getId());
        return account.getId();
    }

    public AccountResponseDTO getAccount(long accountId) {
        Account existingAccount = accountMapper.findById(accountId);

        if (existingAccount != null) {
            log.info("Account with ID {} found", existingAccount.getId());
            List<Transaction> transactions = transactionMapper.getAllByAccountId(accountId);
            Map<String, BigDecimal> initialBalances = new HashMap<>();

            if (isEmpty(transactions)) {
                log.warn("Account with ID {} does not have any transactions", accountId);
                for (String currency : existingAccount.getCurrencies()) {
                    initialBalances.put(currency, new BigDecimal("0.00"));
                }
            }

            List<BalanceDTO> balances = BalanceHelper.getBalances(initialBalances);
            AccountResponseDTO response = accountConverter.convertEntityToDto(existingAccount);

            response.setBalances(balances);

            return response;
        }

        throw new AccountNotFoundException("Account with ID " + accountId + " not found");
    }
}
