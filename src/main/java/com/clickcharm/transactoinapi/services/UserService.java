package com.clickcharm.transactoinapi.services;

import com.clickcharm.transactoinapi.exception.ExpTrackException;
import com.clickcharm.transactoinapi.model.User;

public interface UserService {
    User validateUser(String email, String password) throws ExpTrackException;
    User registerUser(User user) throws ExpTrackException;
}
