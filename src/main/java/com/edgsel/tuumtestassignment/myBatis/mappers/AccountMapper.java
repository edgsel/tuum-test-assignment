package com.edgsel.tuumtestassignment.myBatis.mappers;

import com.edgsel.tuumtestassignment.myBatis.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts")
    List<Account> findAll();

    @Select("SELECT * FROM accounts WHERE id = {id}")
    Account findById(long id);

    @Insert("INSERT INTO accounts(customer_id,country,currencies) VALUES (#{customerId}, #{country}, #{currencies})")
    void insert(Account account);
}
