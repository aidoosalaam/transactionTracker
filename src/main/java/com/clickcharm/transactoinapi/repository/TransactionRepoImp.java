package com.clickcharm.transactoinapi.repository;

import com.clickcharm.transactoinapi.exception.ExpTrackBadRequestException;
import com.clickcharm.transactoinapi.exception.ExpTrackNotFoundException;
import com.clickcharm.transactoinapi.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TransactionRepoImp implements TransactionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String  CREATE =
            "INSERT INTO ET_TRANSACTIONS (TRANSACTION_ID, CATEGORY_ID, USER_ID,NOTE, AMOUNT,TRANSACTION_DATE) " +
                    "VALUES (NEXTVAL('et_transactions_seq'), ?, ?, ?, ?,?)";

    private static final String FETCH_BY_ID = "SELECT * FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID= ? AND TRANSACTION_ID = ?";


    @Override
    public List<Transaction> findAll(int userId, int categoryId) throws ExpTrackNotFoundException {
        return null;
    }

    @Override
    public Transaction findById(int userId, int categoryId, int transactionId) throws ExpTrackNotFoundException {
        try {
            return jdbcTemplate.queryForObject(FETCH_BY_ID, new Object[]{userId,categoryId,transactionId},transactionRowMpaper);
        }catch (Exception e){
            throw new ExpTrackNotFoundException("Invalid Request : " + e.toString());
        }
    }

    @Override
    public Integer create(Transaction transaction) throws ExpTrackBadRequestException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,transaction.getCategoryId());
                ps.setInt(2,transaction.getUserId());
                ps.setString(3,transaction.getTransactionNote());
                ps.setDouble(4,transaction.getAmount());
                ps.setLong(5,transaction.getTimestamp());
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("TRANSACTION_ID");
        }catch (Exception e){
            throw new ExpTrackBadRequestException("Invalid Request : " + e.toString());
        }
    }

    @Override
    public void update(int userId, int transactionId, int categoryId, Transaction newTransaction) throws ExpTrackBadRequestException {

    }

    @Override
    public void removeById(int userId, int categoryId, int transactionId) throws ExpTrackBadRequestException {

    }

    RowMapper<Transaction> transactionRowMpaper = ((rs,rowNum)-> {return new Transaction(
            rs.getInt("TRANSACTION_ID"),
            rs.getInt("CATEGORY_ID"),
            rs.getInt("USER_ID"),
            rs.getString("NOTE"),
            rs.getLong("TRANSACTION_DATE"),
            rs.getDouble("AMOUNT")
    );
    });
}
