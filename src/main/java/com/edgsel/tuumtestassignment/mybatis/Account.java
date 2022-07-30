package com.edgsel.tuumtestassignment.mybatis;

import com.edgsel.tuumtestassignment.mybatis.enums.Currency;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private long id;

    private String customerId;

    private String country;

    private List<Currency> currencies;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;
}
