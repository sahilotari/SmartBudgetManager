package com.codebreakers.SmartBudgetManager.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void testExpenseModel() {
        // Test default constructor
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setAmount(50.0);
        expense.setCategory("Food");
        expense.setDate(LocalDate.of(2025, 5, 14));
        expense.setDescription("Lunch at a restaurant");

        assertEquals(1L, expense.getId());
        assertEquals(50.0, expense.getAmount());
        assertEquals("Food", expense.getCategory());
        assertEquals(LocalDate.of(2025, 5, 14), expense.getDate());
        assertEquals("Lunch at a restaurant", expense.getDescription());

        // Test parameterized constructor
        Expense paramExpense = new Expense(2L, 20.0, "Transport", LocalDate.of(2025, 5, 13), "Taxi ride");
        assertEquals(2L, paramExpense.getId());
        assertEquals(20.0, paramExpense.getAmount());
        assertEquals("Transport", paramExpense.getCategory());
        assertEquals(LocalDate.of(2025, 5, 13), paramExpense.getDate());
        assertEquals("Taxi ride", paramExpense.getDescription());
    }
}
