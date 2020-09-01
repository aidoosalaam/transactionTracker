package com.clickcharm.transactoinapi.services;

import com.clickcharm.transactoinapi.exception.ExpTrackException;
import com.clickcharm.transactoinapi.model.User;
import com.clickcharm.transactoinapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;


@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws ExpTrackException {
       if(email !=null && password != null)
         return  userRepository.findByEmailAndPassword(email,password);
       return null;
    }

    @Override
    public User registerUser(User user) throws ExpTrackException {
        String email = user.getEmail();
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email !=null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches()) {
            throw new ExpTrackException("Invalid Email format");
        }
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0) {
            throw new ExpTrackException("Email already in use");
        }
        Integer userId = userRepository.create(user);
        return userRepository.findById(userId);
    }
}
