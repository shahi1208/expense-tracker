package com.project.expense_tracker.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record createExpenseRequest(
        BigDecimal amount,
        String category,
        String description,
        LocalDate date,
        Long userId
) { }
