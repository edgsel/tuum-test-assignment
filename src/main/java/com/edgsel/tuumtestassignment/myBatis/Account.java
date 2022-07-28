package com.edgsel.tuumtestassignment.myBatis;

import com.edgsel.tuumtestassignment.myBatis.enums.Currency;
import lombok.*;

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
