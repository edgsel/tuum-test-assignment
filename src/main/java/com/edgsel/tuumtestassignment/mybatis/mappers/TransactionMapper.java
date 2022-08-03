package com.edgsel.tuumtestassignment.mybatis.mappers;

import com.edgsel.tuumtestassignment.mybatis.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("SELECT * FROM transactions WHERE id = #{id} AND delete_time IS NULL")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "accountId", column = "account_id"),
        @Result(property = "amount", column = "amount"),
        @Result(property = "currency", column = "currency"),
        @Result(property = "transactionType", column = "transaction_type"),
        @Result(property = "description", column = "description"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time"),
        @Result(property = "delete_time", column = "delete_time")
    })
    Transaction getByTransactionId(long id);

    @Select("SELECT * FROM transactions WHERE account_id = #{accountId} AND delete_time IS NULL")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "accountId", column = "account_id"),
        @Result(property = "amount", column = "amount"),
        @Result(property = "currency", column = "currency"),
        @Result(property = "transactionType", column = "transaction_type"),
        @Result(property = "description", column = "description"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time"),
        @Result(property = "delete_time", column = "delete_time")
    })
    List<Transaction> getAllByAccountId(long accountId);

    @Insert("INSERT INTO transactions(account_id,amount,currency,transaction_type, description) VALUES (#{accountId}, #{amount}, #{currency}, #{transactionType}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Transaction transaction);
}
