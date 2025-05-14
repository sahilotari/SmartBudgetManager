package com.codebreakers.SmartBudgetManager.repo;

import com.codebreakers.SmartBudgetManager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Find all expenses
    List<Expense> findAll();

    // Save a new expense
    <T extends Expense> T save(T expense);

    // Find an expense by ID
    Optional<Expense> findById(Long id);

    // Check if an expense exists by ID
    boolean existsById(Long id);

    // Delete an expense by ID
    void deleteById(Long id);

}