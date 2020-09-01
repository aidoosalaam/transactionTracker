package com.clickcharm.transactoinapi.repository;

import com.clickcharm.transactoinapi.model.User;

public interface UserRepository {

    Integer create(User user);
    User findByEmailAndPassword(String email, String password);
    Integer getCountByEmail(String email);
    User findById(Integer userId);
}
