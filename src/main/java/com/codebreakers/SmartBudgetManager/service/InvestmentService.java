package com.codebreakers.SmartBudgetManager.service;

import com.codebreakers.SmartBudgetManager.model.Investment;
import com.codebreakers.SmartBudgetManager.repo.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    public Investment addInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    public void deleteInvestment(Long id) {
        investmentRepository.deleteById(id);
    }
}
