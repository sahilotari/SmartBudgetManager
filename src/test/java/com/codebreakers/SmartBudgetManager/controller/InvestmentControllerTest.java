package com.codebreakers.SmartBudgetManager.controller;

import com.codebreakers.SmartBudgetManager.model.Investment;
import com.codebreakers.SmartBudgetManager.service.InvestmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InvestmentControllerTest {

    @Mock
    private InvestmentService investmentService;

    @InjectMocks
    private InvestmentController investmentController;

    public InvestmentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInvestments() {
        List<Investment> investments = Arrays.asList(new Investment(), new Investment());
        when(investmentService.getAllInvestments()).thenReturn(investments);

        ResponseEntity<List<Investment>> response = investmentController.getAllInvestments();

        assertNotNull(response);
        assertEquals(2, response.getBody().size());
        verify(investmentService, times(1)).getAllInvestments();
    }

    @Test
    void testAddInvestment() {
        Investment investment = new Investment();
        when(investmentService.addInvestment(investment)).thenReturn(investment);

        ResponseEntity<Investment> response = investmentController.addInvestment(investment);

        assertNotNull(response);
        assertEquals(investment, response.getBody());
        verify(investmentService, times(1)).addInvestment(investment);
    }

    @Test
    void testDeleteInvestment() {
        Long id = 1L;

        ResponseEntity<Void> response = investmentController.deleteInvestment(id);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(investmentService, times(1)).deleteInvestment(id);
    }
}
