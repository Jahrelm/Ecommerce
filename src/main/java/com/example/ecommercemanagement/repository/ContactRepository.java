package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
