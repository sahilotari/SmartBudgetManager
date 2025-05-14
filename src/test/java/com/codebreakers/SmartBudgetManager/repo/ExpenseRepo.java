package com.codebreakers.SmartBudgetManager.repo;

import com.codebreakers.SmartBudgetManager.model.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    void testSaveAndFindById() {
        Expense expense = new Expense(null, 50.0, "Food", LocalDate.of(2025, 5, 14), "Lunch at a restaurant");
        Expense savedExpense = expenseRepository.save(expense);

        Optional<Expense> foundExpense = expenseRepository.findById(savedExpense.getId());
        assertTrue(foundExpense.isPresent());
        assertEquals("Food", foundExpense.get().getCategory());
    }

    @Test
    void testFindAll() {
        Expense expense1 = new Expense(null, 50.0, "Food", LocalDate.of(2025, 5, 14), "Lunch");
        Expense expense2 = new Expense(null, 20.0, "Transport", LocalDate.of(2025, 5, 13), "Taxi");
        expenseRepository.save(expense1);
        expenseRepository.save(expense2);

        List<Expense> expenses = expenseRepository.findAll();
        assertEquals(2, expenses.size());
    }

    @Test
    void testDelete() {
        Expense expense = new Expense(null, 50.0, "Food", LocalDate.of(2025, 5, 14), "Lunch");
        Expense savedExpense = expenseRepository.save(expense);

        expenseRepository.deleteById(savedExpense.getId());
        Optional<Expense> foundExpense = expenseRepository.findById(savedExpense.getId());
        assertFalse(foundExpense.isPresent());
    }
}