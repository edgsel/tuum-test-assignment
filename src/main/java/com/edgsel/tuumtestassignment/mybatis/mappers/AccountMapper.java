package com.edgsel.tuumtestassignment.mybatis.mappers;

import com.edgsel.tuumtestassignment.config.mybatis.typeHandlers.ListArrayTypeHandler;
import com.edgsel.tuumtestassignment.mybatis.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts WHERE id = #{id}")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "country", column = "country"),
        @Result(property = "customerId", column = "customer_id"),
        @Result(property = "currencies", column = "currencies", typeHandler = ListArrayTypeHandler.class, jdbcType = JdbcType.ARRAY),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time"),
        @Result(property = "delete_time", column = "delete_time")
    })
    Account findById(long id);

    @Insert("INSERT INTO accounts(customer_id, country, currencies) VALUES (#{customerId}, #{country}, #{currencies, typeHandler=com.edgsel.tuumtestassignment.config.mybatis.typeHandlers.ListArrayTypeHandler})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Account account);
}
