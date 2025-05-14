CREATE DATABASE IF NOT EXISTS SmartBudgetManagerDB;
USE SmartBudgetManagerDB;


CREATE TABLE Expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10,2) NOT NULL,
    category VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE Investments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    return_rate DECIMAL(5,2),
    maturity_date DATE
);

CREATE TABLE Income (
    id INT AUTO_INCREMENT PRIMARY KEY,
    source VARCHAR(100) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    date DATE NOT NULL
);


-- Expenses
INSERT INTO Expenses (id, amount, category, date, description) VALUES
(1, 120.00, 'Food', '2024-06-01', 'Groceries'),
(2, 45.50, 'Transport', '2024-06-02', 'Taxi fare'),
(3, 200.00, 'Rent', '2024-06-03', 'June rent'),
(4, 30.00, 'Utilities', '2024-06-04', 'Electricity bill'),
(5, 60.00, 'Entertainment', '2024-06-05', 'Movie night');

-- Investments
INSERT INTO Investments (id, type, amount, return_rate, maturity_date) VALUES
(1, 'Stocks', 5000.00, 7.5, '2025-06-01'),
(2, 'Bonds', 2000.00, 5.0, '2026-01-01'),
(3, 'Mutual Funds', 3000.00, 6.2, '2025-12-31'),
(4, 'Real Estate', 10000.00, 8.0, '2027-06-01'),
(5, 'Gold', 1500.00, 4.5, '2025-09-15');

-- Income
INSERT INTO Income (id, source, amount, date) VALUES
(1, 'Salary', 3000.00, '2024-06-01'),
(2, 'Freelance', 800.00, '2024-06-03'),
(3, 'Dividends', 150.00, '2024-06-05'),
(4, 'Interest', 60.00, '2024-06-07'),
(5, 'Gift', 200.00, '2024-06-10');
