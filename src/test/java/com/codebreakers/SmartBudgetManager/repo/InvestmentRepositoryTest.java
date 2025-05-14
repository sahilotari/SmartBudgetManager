package com.codebreakers.SmartBudgetManager.repo;

import com.codebreakers.SmartBudgetManager.model.Investment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InvestmentRepositoryTest {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Test
    void testSaveAndFindAll() {
        Investment investment = new Investment();
        investment.setName("Test Investment");
        investment.setAmount(1000.0);
        investment.setType("Stock");

        investmentRepository.save(investment);

        List<Investment> investments = investmentRepository.findAll();

        assertEquals(1, investments.size());
        assertEquals("Test Investment", investments.get(0).getName());
    }
}