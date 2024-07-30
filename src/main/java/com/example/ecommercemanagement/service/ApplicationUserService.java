package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserService {
    Optional<ApplicationUser> findById(Integer userId);
}
