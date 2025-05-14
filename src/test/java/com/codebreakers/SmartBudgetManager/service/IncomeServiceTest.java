// src/test/java/com/codebreakers/SmartBudgetManager/service/IncomeServiceTest.java
package com.codebreakers.SmartBudgetManager.service;

import com.codebreakers.SmartBudgetManager.model.Income;
import com.codebreakers.SmartBudgetManager.repo.IncomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IncomeServiceTest {

    @Mock
    private IncomeRepository incomeRepository;

    @InjectMocks
    private IncomeService incomeService;

    private Income income;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        income = new Income();
        income.setId(1L);
        income.setAmount(100.0);
        income.setSource("Test");
        income.setDate(LocalDate.now());
        income.setDescription("Test desc");
    }

    @Test
    void testGetAllIncome() {
        when(incomeRepository.findAll()).thenReturn(List.of(income));
        List<Income> result = incomeService.getAllIncome();
        assertEquals(1, result.size());
    }

    @Test
    void testAddIncome() {
        when(incomeRepository.save(any(Income.class))).thenReturn(income);
        Income saved = incomeService.addIncome(income);
        assertEquals(income.getId(), saved.getId());
    }

    @Test
    void testUpdateIncome_Found() {
        when(incomeRepository.findById(1L)).thenReturn(Optional.of(income));
        when(incomeRepository.save(any(Income.class))).thenReturn(income);

        Income updated = new Income();
        updated.setAmount(200.0);
        updated.setSource("Updated");
        updated.setDate(LocalDate.now());
        updated.setDescription("Updated desc");

        Optional<Income> result = incomeService.updateIncome(1L, updated);
        assertTrue(result.isPresent());
        assertEquals(200.0, result.get().getAmount());
    }

    @Test
    void testUpdateIncome_NotFound() {
        when(incomeRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Income> result = incomeService.updateIncome(2L, income);
        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteIncome_Exists() {
        when(incomeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(incomeRepository).deleteById(1L);
        assertTrue(incomeService.deleteIncome(1L));
    }

    @Test
    void testDeleteIncome_NotExists() {
        when(incomeRepository.existsById(2L)).thenReturn(false);
        assertFalse(incomeService.deleteIncome(2L));
    }
}