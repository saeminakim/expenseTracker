package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    ArrayList<Expense> findAll();
    
    @Query(value = "SELECT SUM(INCOME) FROM EXPENSE", nativeQuery = true)
    int getAllIncomeTotal();
    
    @Query(value = "SELECT SUM(EXPENSE) FROM EXPENSE", nativeQuery = true)
    int getAllExpenseTotal();
}
