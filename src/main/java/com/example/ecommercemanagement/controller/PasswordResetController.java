package com.example.ecommercemanagement.controller;


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
    public ResponseEntity<String> requestRequest(@RequestParam String username){
        String token = authenticationService.intiatePasswordReset(username);
        return ResponseEntity.ok("Password reset token: " + token);

    }
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword){
      authenticationService.resetPassword(token, newPassword);
      return ResponseEntity.ok("Password has been reset");
    }





}
