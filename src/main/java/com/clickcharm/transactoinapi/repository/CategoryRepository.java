package com.clickcharm.transactoinapi.repository;

import com.clickcharm.transactoinapi.exception.ExpTrackBadRequestException;
import com.clickcharm.transactoinapi.exception.ExpTrackNotFoundException;
import com.clickcharm.transactoinapi.model.Category;

import java.util.List;

public  interface CategoryRepository {
    List<Category> findAll(int userId) throws ExpTrackNotFoundException;
    Category findById(int userId, int categoryId) throws ExpTrackNotFoundException;
    Integer create(Category category) throws ExpTrackBadRequestException;
    void update(int userId, int categoryId, Category newCategory)throws ExpTrackBadRequestException;
    void removeById(int userId, int categoryId) throws ExpTrackBadRequestException;
}
