package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.Support;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends CrudRepository<Support, Long> {

}
