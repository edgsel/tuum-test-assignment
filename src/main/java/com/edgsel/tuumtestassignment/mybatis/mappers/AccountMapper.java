package com.edgsel.tuumtestassignment.mybatis.mappers;

import com.edgsel.tuumtestassignment.config.mybatis.typeHandlers.ListArrayTypeHandler;
import com.edgsel.tuumtestassignment.mybatis.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts WHERE account_id = #{accountId}")
    @Results(value = {
            @Result(property = "country", column = "country"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "currencies", column = "currencies", typeHandler = ListArrayTypeHandler.class, jdbcType = JdbcType.ARRAY),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "delete_time", column = "delete_time")
    })
    Account findByAccountId(String accountId);

    @Insert("INSERT INTO accounts(account_id, customer_id, country, currencies) VALUES (#{accountId}, #{customerId}, #{country}, #{currencies, typeHandler=com.edgsel.tuumtestassignment.config.mybatis.typeHandlers.ListArrayTypeHandler})")
    void insert(Account account);
}
