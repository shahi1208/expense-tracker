package com.project.expense_tracker.service;

import com.project.expense_tracker.DTO.createExpenseRequest;
import com.project.expense_tracker.entity.Expense;
import com.project.expense_tracker.entity.User;
import com.project.expense_tracker.repository.ExpenseRepository;
import com.project.expense_tracker.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    ExpenseService(ExpenseRepository expenseRepository,  UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public void createExpense(createExpenseRequest request){
        User user = userRepository.findById(request.userId()).orElseThrow(
                () -> new RuntimeException("User not found"));

        Expense newExpense = new Expense();
        newExpense.setAmount(request.amount());
        newExpense.setCategory(request.category());
        newExpense.setDescription(request.description());
        newExpense.setExpenseDate(request.date());
        newExpense.setUser(user);
        log.info("created expense for user {}" , newExpense.getUser());
        expenseRepository.save(newExpense);
    }
}
