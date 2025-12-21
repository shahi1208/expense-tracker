package com.project.expense_tracker.config;

import com.project.expense_tracker.entity.Expense;
import com.project.expense_tracker.entity.User;
import com.project.expense_tracker.repository.ExpenseRepository;
import com.project.expense_tracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Config
 */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadUsers(UserRepository userRepository, ExpenseRepository expenseRepository) {
        return args -> {
            if (expenseRepository.count() == 0) {
                User user = userRepository.findByUsername("admin").orElseThrow();
                Expense expense = new Expense();
                expense.setUser(user);
                expense.setExpenseDate(LocalDate.now());
                expense.setCategory("rent");
                expense.setAmount(new BigDecimal("200"));
                expenseRepository.save(expense);

                System.out.println("Data Load Complete");
            }
        };
    };
}
