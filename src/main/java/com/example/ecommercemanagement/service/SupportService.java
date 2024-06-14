package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.Support;
import com.example.ecommercemanagement.repository.SupportRepository;
import com.example.ecommercemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SupportService {

    @Autowired
    private SupportRepository supportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  EmailService emailService;
    public SupportService(SupportRepository supportRepository, EmailService emailService ,
                          UserRepository userRepository){
        this.supportRepository = supportRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    public Support createSupport(String firstName, String lastName,
                                 String username, String message){

        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong email "));

        Support support = new Support();
        support.setFirstName(firstName);
        support.setLastName(lastName);
        support.setUsername(username);
        support.setMessage(message);

        Support savedSupport = supportRepository.save(support);

        emailService.sendEmail("jmdevtestmobile@gmail.com", "Support Request ", "Message : " + message );

        return savedSupport;
    }


}
