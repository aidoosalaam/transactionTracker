package com.clickcharm.transactoinapi.model;

public class Transaction {
    private int transactionId;
    private int categoryId;
    private int userId;
    private String transactionNote;
    private Long timestamp;
    private double amount;

    public Transaction() {
    }

    public Transaction(int transactionId, int categoryId, int userId, String transactionNote, Long timestamp, double amount) {
        this.transactionId = transactionId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.transactionNote = transactionNote;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", transactionNote='" + transactionNote + '\'' +
                ", timestamp=" + timestamp +
                ", transactionAount=" + amount +
                '}';
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
