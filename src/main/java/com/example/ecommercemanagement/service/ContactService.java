package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.Contact;
import com.example.ecommercemanagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmailService emailService;

    public ContactService(ContactRepository contactRepository, EmailService emailService){
        this.contactRepository = contactRepository;
        this.emailService = emailService;
    }

    public Contact createContact (String firstName, String lastName, String username,
                                  String subject, String message){

        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setUsername(username);
        contact.setSubject(subject);
        contact.setMessage(message);

        Contact savedContact = contactRepository.save(contact);

        String emailBody = "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Username: " + username + "\n" +
                "Subject: " + subject + "\n" +
                "Message: " + message;

        emailService.sendEmail("jmdevtestmobile@gmail.com", "Subject: " + subject, emailBody);

        return savedContact;

    }
}
