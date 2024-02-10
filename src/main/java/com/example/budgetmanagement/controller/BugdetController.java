package com.example.budgetmanagement.controller;

import com.example.budgetmanagement.model.Budget;
import com.example.budgetmanagement.service.IBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="budgets")
public class BugdetController {

    @Autowired
    private IBudgetService budgetService;

    @PostMapping

    public @ResponseBody Budget addBudget(@RequestBody Budget budget){
        Budget budgetInfo =  budgetService.addBudget(budget);
        return budgetInfo;
    }


    public IBudgetService getBudgetService(){
        return budgetService;
    }

    public void setBudgetService(IBudgetService budgetService){
    }
}
