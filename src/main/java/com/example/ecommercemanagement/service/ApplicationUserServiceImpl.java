package com.example.ecommercemanagement.service;


import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService{


    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<ApplicationUser> findById(Integer userId){
        return userRepository.findById(userId);
    }
}
