package com.edgsel.tuumtestassignment.myBatis.mappers;

import com.edgsel.tuumtestassignment.config.typeHandlers.ListArrayTypeHandler;
import com.edgsel.tuumtestassignment.myBatis.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts")
    @Results(value = {
            @Result(property = "country", column = "country", typeHandler = StringTypeHandler.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "customerId", column = "customer_id", typeHandler = StringTypeHandler.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "currencies", column = "currencies", typeHandler = ListArrayTypeHandler.class, jdbcType = JdbcType.ARRAY),
            @Result(property = "createTime", column = "create_time", typeHandler = LocalDateTimeTypeHandler.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updateTime", column = "update_time", typeHandler = LocalDateTimeTypeHandler.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "delete_time", column = "delete_time", typeHandler = LocalDateTimeTypeHandler.class, jdbcType = JdbcType.TIMESTAMP)
    })
    List<Account> findAll();

    @Select("SELECT * FROM accounts WHERE id = {id}")
    @Result(property = "currencies", column = "currencies", typeHandler = ListArrayTypeHandler.class, jdbcType = JdbcType.ARRAY)
    Account findById(long id);

    @Insert("INSERT INTO accounts(customer_id,country,currencies) VALUES (#{customerId}, #{country}, #{currencies, typeHandler=com.edgsel.tuumtestassignment.config.typeHandlers.ListArrayTypeHandler})")
    void insert(Account account);
}
