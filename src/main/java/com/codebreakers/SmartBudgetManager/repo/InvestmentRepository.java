package com.codebreakers.SmartBudgetManager.repo;

import com.codebreakers.SmartBudgetManager.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
