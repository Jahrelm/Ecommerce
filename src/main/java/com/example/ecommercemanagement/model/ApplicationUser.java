package com.example.ecommercemanagement.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")

public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")

    private Integer userId;
    @Column(unique = true)
    private String username;
/*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password_reset_id")
    private PasswordReset reset;
    */

    private String password;
    private String fullName;
    private String phoneNumber;
    private String country;
    private String address;
    private String city;
    private String postCode;
    private String resetToken;

    private LocalDateTime resetTokenExpiry;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_role_junction",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;

    public ApplicationUser(){
        super();
        this.authorities = new HashSet<Role>();

    }

    public ApplicationUser(Integer userId, String username, String password, Set<Role> authorities, String fullName,
                           String phoneNumber, String country, String address, String city, String postCode) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {

        this.userId = userId;
    }


    public void setUsername(String username) {

        this.username = username;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorities;
    }
    public void setAuthorities(Set<Role> authorities) {

        this.authorities = authorities;
    }


    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {

        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenExpiry() {
        return resetTokenExpiry;
    }

    public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }
/*
    public PasswordReset getReset() {
        return reset;
    }

    public void setReset(PasswordReset reset) {
        this.reset = reset;
    }

    public String getNewPassword(){
        return reset.getNewPassword();
    }
    */

}
