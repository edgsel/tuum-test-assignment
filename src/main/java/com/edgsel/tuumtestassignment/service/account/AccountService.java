package com.edgsel.tuumtestassignment.service.account;

import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.converter.account.AccountConverter;
import com.edgsel.tuumtestassignment.exception.AccountNotFoundException;
import com.edgsel.tuumtestassignment.helper.BalanceHelper;
import com.edgsel.tuumtestassignment.mybatis.Account;
import com.edgsel.tuumtestassignment.mybatis.Transaction;
import com.edgsel.tuumtestassignment.mybatis.mappers.AccountMapper;
import com.edgsel.tuumtestassignment.mybatis.mappers.TransactionMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
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

    public long saveAccount(AccountRequestDTO accountRequestDTO) {
        Account account = accountConverter.convertDtoToEntity(accountRequestDTO);

        accountMapper.insert(account);

        return account.getId();
    }

    public AccountResponseDTO getAccount(long accountId) {
        Account existingAccount = accountMapper.findById(accountId);

        if (existingAccount != null) {
            List<Transaction> transactions = transactionMapper.getAllByAccountId(accountId);
            Map<String, BigDecimal> initialBalances = new HashMap<>();

            if (isEmpty(transactions)) {
                for (String currency : existingAccount.getCurrencies()) {
                    initialBalances.put(currency, new BigDecimal("0.00"));
                }
            }

            List<BalanceDTO> balances = BalanceHelper.getBalances(initialBalances);
            AccountResponseDTO response = accountConverter.entityToDto(existingAccount);

            response.setBalances(balances);

            return response;
        }

        throw new AccountNotFoundException("Account with ID " + accountId + " not found");
    }
}
