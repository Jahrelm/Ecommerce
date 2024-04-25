package com.example.ecommercemanagement.model;

public class RegistrationDTO {
    private String username;
    private String password;

    private String fullName;
    private String phoneNumber;
    private String country;
    private String address;

    private String city;

    private String postCode;

    public RegistrationDTO(){
        super();
    }
    public RegistrationDTO
            (String username, String password, String fullName,
             String phoneNumber, String country, String address, String city, String postCode) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public String toString(){
        return (
                "Registration info: Email : " + this.username +
                "password : "+ this.password + "Full Name : " + this.fullName + "Phone Number : " + this.phoneNumber
                + "Country : " + this.country + "Addresss : " + this.address + "City : " + this.city +
                        "Post Code :" + this.postCode);
    }
}
