package com.clickcharm.transactoinapi.services;

import com.clickcharm.transactoinapi.exception.ExpTrackBadRequestException;
import com.clickcharm.transactoinapi.exception.ExpTrackNotFoundException;
import com.clickcharm.transactoinapi.model.Transaction;
import com.clickcharm.transactoinapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImp implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> fetchAllTransactions(int userId, int categoryId) {
        return null;
    }

    @Override
    public Transaction fetchTransactionById(int userId, int categoryId, int transactionId) throws ExpTrackNotFoundException {
        return transactionRepository.findById(userId,categoryId,transactionId);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws ExpTrackBadRequestException {
        int transId =  transactionRepository.create(transaction);
        return transactionRepository.findById(transaction.getUserId(),transaction.getCategoryId(),transId);
    }

    @Override
    public void updateTransaction(int userId, int transactionId, int categoryId, Transaction newTransaction) throws ExpTrackBadRequestException {

    }

    @Override
    public void removeWithAllTransactions(int userId, int categoryId, int transactionId) throws ExpTrackNotFoundException {

    }
}
