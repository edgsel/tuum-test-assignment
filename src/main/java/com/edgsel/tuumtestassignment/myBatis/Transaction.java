package com.edgsel.tuumtestassignment.myBatis;

import com.edgsel.tuumtestassignment.myBatis.enums.Currency;
import com.edgsel.tuumtestassignment.myBatis.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper
@Getter
@Setter
public class Transaction {

    private Long id;

    private Account account;

    private BigDecimal amount;

    private Currency currency;

    private TransactionType transactionType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;
}
