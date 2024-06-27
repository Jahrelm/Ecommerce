package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.Contact;
import com.example.ecommercemanagement.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@CrossOrigin("https://localhost:3000")
public class ContactController {

    @Autowired
    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @PostMapping("/request")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contactInfo) {
        Contact contact = contactService.createContact(
                contactInfo.getFirstName(),
                contactInfo.getLastName(),
                contactInfo.getUsername(),
                contactInfo.getSubject(),
                contactInfo.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }
}


