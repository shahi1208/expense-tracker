package com.project.expense_tracker.controller;

import com.project.expense_tracker.DTO.createExpenseRequest;
import com.project.expense_tracker.entity.Expense;
import com.project.expense_tracker.repository.ExpenseRepository;
import com.project.expense_tracker.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * All API for Entity
 */
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);
    private final ExpenseRepository expenseRepository;
    private final ExpenseService expenseService;

     ExpenseController(ExpenseRepository expenseRepository,  ExpenseService expenseService) {
        this.expenseRepository = expenseRepository;
        this.expenseService = expenseService;
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

    @PostMapping
    public ResponseEntity<Void> createExpense(@RequestBody createExpenseRequest request) {
        expenseService.createExpense(request);
        return ResponseEntity.noContent().build();
    }
}