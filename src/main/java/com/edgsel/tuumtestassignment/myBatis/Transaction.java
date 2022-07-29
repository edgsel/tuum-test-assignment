package com.edgsel.tuumtestassignment.myBatis;

import com.edgsel.tuumtestassignment.myBatis.enums.Currency;
import com.edgsel.tuumtestassignment.myBatis.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    private long id;

    private String transactionId;

    private String accountId;

    private BigDecimal amount;

    private Currency currency;

    private TransactionType transactionType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;
}
