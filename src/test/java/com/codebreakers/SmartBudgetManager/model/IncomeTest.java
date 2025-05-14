// src/test/java/com/codebreakers/SmartBudgetManager/model/IncomeTest.java
package com.codebreakers.SmartBudgetManager.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IncomeTest {

    @Test
    void testGettersAndSetters() {
        Income income = new Income();
        income.setId(1L);
        income.setAmount(100.0);
        income.setSource("TestSource");
        income.setDate(LocalDate.of(2025, 5, 1));
        income.setDescription("TestDesc");

        assertEquals(1L, income.getId());
        assertEquals(100.0, income.getAmount());
        assertEquals("TestSource", income.getSource());
        assertEquals(LocalDate.of(2025, 5, 1), income.getDate());
        assertEquals("TestDesc", income.getDescription());
    }
}