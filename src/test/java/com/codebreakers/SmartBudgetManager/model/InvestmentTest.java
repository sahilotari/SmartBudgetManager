package com.codebreakers.SmartBudgetManager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestmentTest {

    @Test
    void testInvestmentGettersAndSetters() {
        Investment investment = new Investment();

        investment.setId(1L);
        investment.setName("Test Investment");
        investment.setAmount(5000.0);
        investment.setType("Stock");

        assertEquals(1L, investment.getId());
        assertEquals("Test Investment", investment.getName());
        assertEquals(5000.0, investment.getAmount());
        assertEquals("Stock", investment.getType());
    }

    @Test
    void testInvestmentEqualsAndHashCode() {
        Investment investment1 = new Investment();
        investment1.setId(1L);
        investment1.setName("Test Investment");
        investment1.setAmount(5000.0);
        investment1.setType("Stock");

        Investment investment2 = new Investment();
        investment2.setId(1L);
        investment2.setName("Test Investment");
        investment2.setAmount(5000.0);
        investment2.setType("Stock");

        assertEquals(investment1, investment2);
        assertEquals(investment1.hashCode(), investment2.hashCode());
    }

    @Test
    void testInvestmentToString() {
        Investment investment = new Investment();
        investment.setId(1L);
        investment.setName("Test Investment");
        investment.setAmount(5000.0);
        investment.setType("Stock");

        String expected = "Investment(id=1, name=Test Investment, amount=5000.0, type=Stock)";
        assertEquals(expected, investment.toString());
    }
}
