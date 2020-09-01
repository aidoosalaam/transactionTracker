package com.clickcharm.transactoinapi.repository;

import com.clickcharm.transactoinapi.exception.ExpTrackBadRequestException;
import com.clickcharm.transactoinapi.exception.ExpTrackNotFoundException;
import com.clickcharm.transactoinapi.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll(int userId, int categoryId) throws ExpTrackNotFoundException;
    Transaction findById(int userId, int categoryId,int transactionId) throws ExpTrackNotFoundException;
    Integer create(Transaction transaction) throws ExpTrackBadRequestException;
    void update(int userId, int transactionId,int categoryId ,Transaction newTransaction)throws ExpTrackBadRequestException;
    void removeById(int userId, int categoryId, int transactionId) throws ExpTrackBadRequestException;
}
