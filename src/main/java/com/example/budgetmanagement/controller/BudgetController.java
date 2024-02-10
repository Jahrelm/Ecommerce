package com.example.budgetmanagement.controller;

import com.example.budgetmanagement.model.Budget;
import com.example.budgetmanagement.service.IBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="budgets")
public class BudgetController {

    @Autowired
    private IBudgetService budgetService;

    @PostMapping
    public @ResponseBody Budget addBudget(@RequestBody Budget budget){
        Budget budgetInfo =  budgetService.addBudget(budget);
        return budgetInfo;
    }
    @PutMapping
    public @ResponseBody Budget updateBudget(@RequestBody Budget budget){
        Budget updatedBudget = budgetService.updateBudget(budget);
        return updatedBudget;
    }
    @DeleteMapping(path= "/{id}")
    public  @ResponseBody String deleteBudget(@PathVariable Integer id){
        return budgetService.deleteBudget(id);
    }

    @GetMapping(path="/{id}")
    public  @ResponseBody Budget getBudget(@PathVariable Integer id){
      return budgetService.getBudget(id);
    }

    public IBudgetService getBudgetService(){
        return budgetService;
    }

    public void setBudgetService(IBudgetService budgetService){
    }
}
