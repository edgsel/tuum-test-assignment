package com.edgsel.tuumtestassignment.myBatis.mappers;

import com.edgsel.tuumtestassignment.myBatis.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("SELECT * FROM transactions WHERE transaction_id = #{transactionId} AND delete_time IS NULL")
    @Results(value = {
            @Result(property = "transactionId", column = "transaction_id"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "currency", column = "currency"),
            @Result(property = "transactionType", column = "transaction_type"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "delete_time", column = "delete_time")
    })
    Transaction getByTransactionId(String transactionId);

    @Select("SELECT * FROM transactions WHERE account_id = #{accountId} AND delete_time IS NULL")
    @Results(value = {
            @Result(property = "transactionId", column = "transaction_id"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "currency", column = "currency"),
            @Result(property = "transactionType", column = "transaction_type"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "delete_time", column = "delete_time")
    })
    List<Transaction> getAllByAccountId(String accountId);

    @Insert("INSERT INTO transactions(transaction_id, account_id,amount,currency,transaction_type) VALUES (#{transactionId}, #{accountId}, #{amount}, #{currency}, #{transactionType})")
    void insert(Transaction transaction);
}
