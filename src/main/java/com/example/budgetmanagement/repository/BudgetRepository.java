package com.example.budgetmanagement.repository;

import com.example.budgetmanagement.model.Budget;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<Budget, Integer> {


}
