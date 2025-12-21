package com.project.expense_tracker.service;

import com.project.expense_tracker.DTO.createUserRequest;
import com.project.expense_tracker.entity.User;
import com.project.expense_tracker.repository.ExpenseRepository;
import com.project.expense_tracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

/**
 * Service class for User entity.
 * All business transaction, validation will occur here.
 */
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    UserService(UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public void createUser(createUserRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setPassword(request.password());
        newUser.setEmail(request.email());
        newUser.setCreatedAt(LocalDateTime.now());
        log.info("User created: {}", newUser);
        userRepository.save(newUser);
    }

    @Transactional
    public void deleteUserByUsername(String username){
        var user = findOrThrow(username);
        validateDeletion(user);
        userRepository.delete(user);
        log.info("Deleted user with username: {}" ,  user.getUsername());
    }

    /**
     * Return User entity of the given username exists else throw appropriate error
     * @param username String username
     * @return User entity if present
     */
    private User findOrThrow(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    /**
     * Check if expense exists for given username.
     * if yes, throw conflict as deletion of user with expense entry is prohibited
     * @param user Entity
     */
    private void validateDeletion(User user){
        var userId = user.getId();
        if (expenseRepository.existsByUserId(userId))
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Cannot delete user :" + user.getId() + " as expense already exists");
    }

}
