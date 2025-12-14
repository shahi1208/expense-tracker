package com.project.expense_tracker.controller;

import com.project.expense_tracker.entity.Expense;
import com.project.expense_tracker.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseRepository expenseRepository;

     ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    @GetMapping("/{category}")
    public List<Expense> getExpenseByCategory(@PathVariable String category){
        var expenses =  expenseRepository.findByCategory(category);
        if (expenses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense category not found");
        }
        return expenses;
    }
}
