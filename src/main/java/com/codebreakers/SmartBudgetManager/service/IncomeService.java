package com.codebreakers.SmartBudgetManager.service;

import com.codebreakers.SmartBudgetManager.model.Income;
import com.codebreakers.SmartBudgetManager.repo.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public Optional<Income> updateIncome(Long id, Income updatedIncome) {
        return incomeRepository.findById(id).map(income -> {
            income.setAmount(updatedIncome.getAmount());
            income.setSource(updatedIncome.getSource());
            income.setDate(updatedIncome.getDate());
            income.setDescription(updatedIncome.getDescription());
            return incomeRepository.save(income);
        });
    }

    public boolean deleteIncome(Long id) {
        if (incomeRepository.existsById(id)) {
            incomeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}