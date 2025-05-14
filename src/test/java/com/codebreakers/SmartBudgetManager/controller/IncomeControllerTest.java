// src/test/java/com/codebreakers/SmartBudgetManager/controller/IncomeControllerTest.java
package com.codebreakers.SmartBudgetManager.controller;

import com.codebreakers.SmartBudgetManager.model.Income;
import com.codebreakers.SmartBudgetManager.repo.IncomeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IncomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Income income;

    @BeforeEach
    void setUp() {
        incomeRepository.deleteAll();
        income = new Income();
        income.setAmount(123.0);
        income.setSource("ControllerTest");
        income.setDate(LocalDate.now());
        income.setDescription("Controller test desc");
        income = incomeRepository.save(income);
    }

    @Test
    void testGetAllIncome() throws Exception {
        mockMvc.perform(get("/api/income"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].source").value("ControllerTest"));
    }

    @Test
    void testAddIncome() throws Exception {
        Income newIncome = new Income();
        newIncome.setAmount(456.0);
        newIncome.setSource("NewSource");
        newIncome.setDate(LocalDate.now());
        newIncome.setDescription("New desc");

        mockMvc.perform(post("/api/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newIncome)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Income entry added successfully."));
    }

    @Test
    void testUpdateIncome() throws Exception {
        income.setAmount(789.0);
        mockMvc.perform(put("/api/income/" + income.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(income)))
                .andExpect(status().isOk())
                .andExpect(content().string("Income entry updated successfully."));
    }

    @Test
    void testUpdateIncome_NotFound() throws Exception {
        Income updated = new Income();
        updated.setAmount(999.0);
        updated.setSource("NotFound");
        updated.setDate(LocalDate.now());
        updated.setDescription("Not found");

        mockMvc.perform(put("/api/income/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Income entry not found."));
    }

    @Test
    void testDeleteIncome() throws Exception {
        mockMvc.perform(delete("/api/income/" + income.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteIncome_NotFound() throws Exception {
        mockMvc.perform(delete("/api/income/99999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Income entry not found."));
    }
}