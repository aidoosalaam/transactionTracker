package com.clickcharm.transactoinapi.services;

import com.clickcharm.transactoinapi.exception.ExpTrackBadRequestException;
import com.clickcharm.transactoinapi.exception.ExpTrackNotFoundException;
import com.clickcharm.transactoinapi.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> fetchAllTransactions(int userId, int categoryId);
    Transaction fetchTransactionById(int userId, int categoryId,int transactionId) throws ExpTrackNotFoundException;
    Transaction addTransaction(Transaction transaction) throws ExpTrackBadRequestException;
    void updateTransaction(int userId, int transactionId,int categoryId ,Transaction newTransaction)throws ExpTrackBadRequestException;
    void removeWithAllTransactions(int userId, int categoryId, int transactionId) throws ExpTrackNotFoundException;
}
