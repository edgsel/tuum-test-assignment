package com.edgsel.tuumtestassignment.myBatis;

import com.edgsel.tuumtestassignment.myBatis.enums.Currency;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Getter
@Setter
public class Account {

    private Long id;

    private Long customerId;

    private String country;

    private List<Currency> currency;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;
}
