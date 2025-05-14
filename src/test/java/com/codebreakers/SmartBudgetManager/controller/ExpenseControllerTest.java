package com.codebreakers.SmartBudgetManager.controller;

import com.codebreakers.SmartBudgetManager.model.Expense;
import com.codebreakers.SmartBudgetManager.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    public ExpenseControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllExpenses() {
        List<Expense> mockExpenses = Arrays.asList(
                new Expense(1L, 50.0, "Food", LocalDate.now(), "Lunch"),
                new Expense(2L, 20.0, "Transport", LocalDate.now(), "Taxi")
        );
        when(expenseService.getAllExpenses()).thenReturn(mockExpenses);

        ResponseEntity<List<Expense>> response = expenseController.getAllExpenses();

        assertEquals(OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(expenseService, times(1)).getAllExpenses();
    }

    @Test
    void testAddExpense() {
        Expense expense = new Expense(null, 50.0, "Food", LocalDate.now(), "Lunch");
        doNothing().when(expenseService).addExpense(expense);

        ResponseEntity<String> response = expenseController.addExpense(expense);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals("Expense added successfully.", response.getBody());
        verify(expenseService, times(1)).addExpense(expense);
    }

    @Test
    void testUpdateExpense() {
        Expense updatedExpense = new Expense(1L, 60.0, "Food", LocalDate.now(), "Dinner");
        when(expenseService.updateExpense(1L, updatedExpense)).thenReturn(Optional.of(updatedExpense));

        ResponseEntity<String> response = expenseController.updateExpense(1L, updatedExpense);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Expense updated successfully.", response.getBody());
        verify(expenseService, times(1)).updateExpense(1L, updatedExpense);
    }

    @Test
    void testUpdateExpenseNotFound() {
        Expense updatedExpense = new Expense(1L, 60.0, "Food", LocalDate.now(), "Dinner");
        when(expenseService.updateExpense(1L, updatedExpense)).thenReturn(Optional.empty());

        ResponseEntity<String> response = expenseController.updateExpense(1L, updatedExpense);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("Expense not found.", response.getBody());
        verify(expenseService, times(1)).updateExpense(1L, updatedExpense);
    }

    @Test
    void testDeleteExpense() {
        when(expenseService.deleteExpense(1L)).thenReturn(true);

        ResponseEntity<Void> response = expenseController.deleteExpense(1L);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(expenseService, times(1)).deleteExpense(1L);
    }

    @Test
    void testDeleteExpenseNotFound() {
        when(expenseService.deleteExpense(1L)).thenReturn(false);

        ResponseEntity<Void> response = expenseController.deleteExpense(1L);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(expenseService, times(1)).deleteExpense(1L);
    }
}