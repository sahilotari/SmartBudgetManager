package com.codebreakers.SmartBudgetManager.controller;

import com.codebreakers.SmartBudgetManager.model.Investment;
import com.codebreakers.SmartBudgetManager.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @GetMapping
    public ResponseEntity<List<Investment>> getAllInvestments() {
        return ResponseEntity.ok(investmentService.getAllInvestments());
    }

    @PostMapping
    public ResponseEntity<Investment> addInvestment(@RequestBody Investment investment) {
        return ResponseEntity.ok(investmentService.addInvestment(investment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        investmentService.deleteInvestment(id);
        return ResponseEntity.noContent().build();
    }
}