package com.project.expense_tracker.repository;

import com.project.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for User Entity
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
    boolean existsByUsername(String username);
}
