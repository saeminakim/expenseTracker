package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    ArrayList<Expense> findAll();

}
