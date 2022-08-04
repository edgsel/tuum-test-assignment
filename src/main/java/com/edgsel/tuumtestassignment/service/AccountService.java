package com.edgsel.tuumtestassignment.service;

import com.edgsel.tuumtestassignment.client.RabbitClient;
import com.edgsel.tuumtestassignment.controller.dto.request.AccountRequestDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.AccountResponseDTO;
import com.edgsel.tuumtestassignment.controller.dto.response.BalanceDTO;
import com.edgsel.tuumtestassignment.converter.AccountConverter;
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
import static com.edgsel.tuumtestassignment.util.BalanceUtil.getInitialBalances;
import static com.edgsel.tuumtestassignment.util.BalanceUtil.mapBalancesToDto;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Slf4j
public class AccountService {

    private final AccountConverter accountConverter;

    private final AccountMapper accountMapper;

    private final TransactionMapper transactionMapper;

    private final RabbitClient rabbitClient;

    public AccountService(
        AccountConverter accountConverter,
        AccountMapper accountMapper,
        TransactionMapper transactionMapper,
        RabbitClient rabbitClient
    ) {
        this.accountConverter = accountConverter;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
        this.rabbitClient = rabbitClient;
    }

    public long saveAccount(AccountRequestDTO accountRequest) {
        Account account = accountConverter.convertDtoToEntity(accountRequest);

        accountMapper.insert(account);

        log.info("Account with ID {} saved into database", account.getId());
        rabbitClient.send(account, "create");

        return account.getId();
    }

    public AccountResponseDTO getAccount(long accountId) {
        Account existingAccount = accountMapper.findById(accountId);

        if (existingAccount != null) {
            log.info("Account with ID {} found", existingAccount.getId());

            List<Transaction> transactions = transactionMapper.getAllByAccountId(existingAccount.getId());
            List<BalanceDTO> mappedBalances = getCalculatedBalances(transactions, existingAccount);

            AccountResponseDTO response = accountConverter.convertEntityToDto(existingAccount);
            response.setBalances(mappedBalances);

            return response;
        }

        throw new EntityNotFoundException("Account with ID " + accountId + " not found");
    }

    private List<BalanceDTO> getCalculatedBalances(
        List<Transaction> existingTransactions,
        Account existingAccount
    ) {
        Map<String, BigDecimal> balances;
        if (isEmpty(existingTransactions)) {
            log.warn("Account with ID {} does not have any transactions", existingAccount.getId());
            balances = getInitialBalances(existingAccount.getCurrencies());
        } else {
            log.info("Found {} transactions for account ID {}", existingTransactions.size(), existingAccount.getId());
            balances = calculateBalances(existingTransactions);
        }

        return mapBalancesToDto(balances);
    }
}
