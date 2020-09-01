package com.clickcharm.transactoinapi.services;

import com.clickcharm.transactoinapi.exception.ExpTrackBadRequestException;
import com.clickcharm.transactoinapi.exception.ExpTrackNotFoundException;
import com.clickcharm.transactoinapi.model.Category;


import java.util.List;

public interface CategoryService {
    List<Category> fetchAllCategories(int userId);
    Category fetchCategoryById(int userId, int categoryId) throws ExpTrackNotFoundException;
    Category addCategory(Category category) throws ExpTrackBadRequestException;
    void updateCategory(int userId, int categoryId, Category newCategory)throws ExpTrackBadRequestException;
    void removeCategoryWithAllTransactions(int userId, int categoryId) throws ExpTrackNotFoundException;
}
