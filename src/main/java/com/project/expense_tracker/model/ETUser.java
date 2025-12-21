package com.project.expense_tracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Expense Tracker Model for User Entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ETUser {

    private Long id;
    private String username;
    private String email;

}
