// src/test/java/com/codebreakers/SmartBudgetManager/repo/IncomeRepositoryTest.java
package com.codebreakers.SmartBudgetManager.repo;

import com.codebreakers.SmartBudgetManager.model.Income;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IncomeRepositoryTest {

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    void testSaveAndFind() {
        Income income = new Income();
        income.setAmount(100.0);
        income.setSource("RepoTest");
        income.setDate(LocalDate.now());
        income.setDescription("Repo test desc");

        Income saved = incomeRepository.save(income);
        assertNotNull(saved.getId());

        List<Income> all = incomeRepository.findAll();
        assertFalse(all.isEmpty());
    }
}