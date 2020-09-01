package com.clickcharm.transactoinapi.controllers;

import com.clickcharm.transactoinapi.model.Transaction;
import com.clickcharm.transactoinapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request, @RequestBody Transaction transaction){
        int userId = (int) request.getAttribute("userId");

       // int userId=4;
        transaction.setUserId(userId);
        Date date = new Date();
        long datetime = date.getTime();
        transaction.setTimestamp(datetime);
        System.out.println("Transaction adding : " + transaction.toString());
        Transaction transactionCreated = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(transactionCreated, HttpStatus.CREATED);
    }
}
