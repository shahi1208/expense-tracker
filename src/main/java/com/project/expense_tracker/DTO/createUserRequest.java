package com.project.expense_tracker.DTO;

public record createUserRequest(
        String username,
        String password,
        String email
) {
}