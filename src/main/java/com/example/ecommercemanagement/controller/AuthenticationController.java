package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.LoginResponseDTO;
import com.example.ecommercemanagement.model.RegistrationDTO;
import com.example.ecommercemanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(
                body.getUsername(), body.getPassword(), body.getFullName(), body.getPhoneNumber(),
                body.getCountry(), body.getAddress(), body.getCity(), body.getPostCode());

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody RegistrationDTO body){
        LoginResponseDTO response = authenticationService.loginUser(body.getUsername(), body.getPassword());
        if (response == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        return ResponseEntity.ok(response);

    }
}
