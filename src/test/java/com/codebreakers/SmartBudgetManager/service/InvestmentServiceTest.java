package com.codebreakers.SmartBudgetManager.service;

import com.codebreakers.SmartBudgetManager.model.Investment;
import com.codebreakers.SmartBudgetManager.repo.InvestmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InvestmentServiceTest {

    @Mock
    private InvestmentRepository investmentRepository;

    @InjectMocks
    private InvestmentService investmentService;

    public InvestmentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInvestments() {
        List<Investment> investments = Arrays.asList(new Investment(), new Investment());
        when(investmentRepository.findAll()).thenReturn(investments);

        List<Investment> result = investmentService.getAllInvestments();

        assertEquals(2, result.size());
        verify(investmentRepository, times(1)).findAll();
    }

    @Test
    void testAddInvestment() {
        Investment investment = new Investment();
        when(investmentRepository.save(investment)).thenReturn(investment);

        Investment result = investmentService.addInvestment(investment);

        assertNotNull(result);
        verify(investmentRepository, times(1)).save(investment);
    }

    @Test
    void testDeleteInvestment() {
        Long id = 1L;

        investmentService.deleteInvestment(id);

        verify(investmentRepository, times(1)).deleteById(id);
    }
}
