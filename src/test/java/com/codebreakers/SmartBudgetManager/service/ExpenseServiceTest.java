package com.codebreakers.SmartBudgetManager.service;

import com.codebreakers.SmartBudgetManager.model.Expense;
import com.codebreakers.SmartBudgetManager.repo.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    public ExpenseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllExpenses() {
        List<Expense> mockExpenses = Arrays.asList(
                new Expense(1L, 50.0, "Food", LocalDate.now(), "Lunch"),
                new Expense(2L, 20.0, "Transport", LocalDate.now(), "Taxi")
        );
        when(expenseRepository.findAll()).thenReturn(mockExpenses);

        List<Expense> expenses = expenseService.getAllExpenses();

        assertEquals(2, expenses.size());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void testAddExpense() {
        Expense expense = new Expense(null, 50.0, "Food", LocalDate.now(), "Lunch");
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense savedExpense = expenseService.addExpense(expense);

        assertNotNull(savedExpense);
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void testUpdateExpense() {
        Expense existingExpense = new Expense(1L, 50.0, "Food", LocalDate.now(), "Lunch");
        Expense updatedExpense = new Expense(1L, 60.0, "Food", LocalDate.now(), "Dinner");

        when(expenseRepository.findById(1L)).thenReturn(Optional.of(existingExpense));
        when(expenseRepository.save(existingExpense)).thenReturn(updatedExpense);

        Optional<Expense> result = expenseService.updateExpense(1L, updatedExpense);

        assertTrue(result.isPresent());
        assertEquals(60.0, result.get().getAmount());
        verify(expenseRepository, times(1)).findById(1L);
        verify(expenseRepository, times(1)).save(existingExpense);
    }

    @Test
    void testDeleteExpense() {
        when(expenseRepository.existsById(1L)).thenReturn(true);

        boolean result = expenseService.deleteExpense(1L);

        assertTrue(result);
        verify(expenseRepository, times(1)).existsById(1L);
        verify(expenseRepository, times(1)).deleteById(1L);
    }
}
