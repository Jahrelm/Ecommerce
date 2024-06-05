package com.example.ecommercemanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name="PasswordRequest")
public class PasswordRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
