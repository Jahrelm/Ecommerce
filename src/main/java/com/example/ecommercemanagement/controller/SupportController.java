package com.example.ecommercemanagement.controller;


import com.example.ecommercemanagement.model.Support;
import com.example.ecommercemanagement.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/support")
@CrossOrigin("http://localhost:3000")
public class SupportController {

    @Autowired
    private SupportService supportService;
    public SupportController(SupportService supportService){
        this.supportService = supportService;
    }

    @PostMapping("/request")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Support> createSupport(@RequestBody Support supportInfo){
           Support support = supportService.createSupport(
                   supportInfo.getFirstName(),
                   supportInfo.getLastName(),
                   supportInfo.getUsername(),
                   supportInfo.getMessage()

           );
           return ResponseEntity.status(HttpStatus.CREATED).body(support);

    }
}
