package com.example.ecommercemanagement.controller;


import com.example.ecommercemanagement.model.PasswordRequest;
import com.example.ecommercemanagement.model.PasswordReset;
import com.example.ecommercemanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/password")
@CrossOrigin("http://localhost:3000")
public class PasswordResetController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/reset-request")
    public ResponseEntity<String> passwordRequest(@RequestBody PasswordRequest request){
        String token = authenticationService.intiatePasswordReset(request.getUsername());
        return ResponseEntity.ok("Password reset token: " + token);

    }
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordReset reset){
      authenticationService.resetPassword(reset.getToken(), reset.getNewPassword());
      return ResponseEntity.ok("Password has been reset");
    }





}
