package com.project.expense_tracker.repository;

import com.project.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Expense Entity
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(String category);
    Optional<Expense> findByUserId(Long userId);
    boolean existsByUserId(Long expenseId);
}
