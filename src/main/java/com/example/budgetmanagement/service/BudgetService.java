package com.example.budgetmanagement.service;

import com.example.budgetmanagement.model.Budget;
import com.example.budgetmanagement.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService implements IBudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public BudgetRepository getBudgetRepository(){
        return budgetRepository;
    }

    public void setBudgetRepository(BudgetRepository budgetRepository){
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget addBudget(Budget budget) {
     Budget budgetInfo = budgetRepository.save(budget);
     return budgetInfo;
    }
}

