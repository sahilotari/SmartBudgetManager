package com.codebreakers.SmartBudgetManager.controller;

import com.codebreakers.SmartBudgetManager.model.Income;
import com.codebreakers.SmartBudgetManager.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<Income> getAllIncome() {
        return incomeService.getAllIncome();
    }

    @PostMapping
    public ResponseEntity<?> addIncome(@RequestBody Income income) {
        incomeService.addIncome(income);
        return ResponseEntity.status(201).body("Income entry added successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody Income income) {
        return incomeService.updateIncome(id, income)
                .map(updated -> ResponseEntity.ok("Income entry updated successfully."))
                .orElse(ResponseEntity.status(404).body("Income entry not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
        if (incomeService.deleteIncome(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body("Income entry not found.");
        }
    }
}