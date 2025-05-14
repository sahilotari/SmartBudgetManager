package com.codebreakers.SmartBudgetManager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double amount;
    private String type;
}