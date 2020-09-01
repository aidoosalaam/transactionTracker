package com.clickcharm.transactoinapi.services;

import com.clickcharm.transactoinapi.exception.ExpTrackBadRequestException;
import com.clickcharm.transactoinapi.model.Category;
import com.clickcharm.transactoinapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> fetchAllCategories(int userId) {
        return categoryRepository.findAll(userId);
    }

    @Override
    public Category fetchCategoryById(int userId, int categoryId) throws ExpTrackBadRequestException {
        return categoryRepository.findById(userId,categoryId);
    }

    @Override
    public Category addCategory(Category category) throws ExpTrackBadRequestException {
        System.out.println("Category : " + category.toString());
        int catId = categoryRepository.create(category);
        System.out.println("Category id created  : " + catId);
        return categoryRepository.findById(category.getUserId(),catId);
    }

    @Override
    public void updateCategory(int userId, int categoryId, Category newCategory) throws ExpTrackBadRequestException {
        categoryRepository.update(userId,categoryId,newCategory);
    }

    @Override
    public void removeCategoryWithAllTransactions(int userId, int categoryId) throws ExpTrackBadRequestException {

    }
}
