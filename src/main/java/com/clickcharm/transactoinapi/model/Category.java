package com.clickcharm.transactoinapi.model;

public class Category {
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String description;
    private Double totalExpense;

    public Category() {
    }


    public Category(Integer userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Category(Integer categoryId, Integer userId, String title, String description, Double totalExpense) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.totalExpense = totalExpense;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", totalExpense=" + totalExpense +
                '}';
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
