package com.example.budgetmanagement.service;

import com.example.budgetmanagement.model.Budget;
import com.example.budgetmanagement.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Budget getBudget(Integer id){
     Optional<Budget> budgetOptional = budgetRepository.findById(id);
     return budgetOptional.orElse(null);
    }
    @Override
    public Budget updateBudget(Budget budget) {
        try {
            Budget budgetDb = budgetRepository.findById(budget.getId())
                    .orElseThrow(() -> new RuntimeException("Budget doesn't exist"));
            budgetDb.setBudgetName(budget.getBudgetName());
            budgetDb.setTotalIncome(budget.getTotalIncome());
            // set other properties
            return budgetRepository.save(budgetDb);
        } catch (RuntimeException e) {
            throw e;
        }
    }

}

