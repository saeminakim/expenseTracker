package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    ArrayList<Expense> findAll();

    Optional<Expense> findById(Long id);

    @Query("SELECT d FROM Expense d WHERE FUNCTION('YEAR', d.date) = :year AND FUNCTION('MONTH', d.date) = :month")
    ArrayList<Expense> findByMonthAndYear(@Param("year") int year, @Param("month") int month);
    
    @Query(value = "SELECT SUM(INCOME) FROM EXPENSE", nativeQuery = true)
    int getAllIncomeTotal();
    
    @Query(value = "SELECT SUM(EXPENSE) FROM EXPENSE", nativeQuery = true)
    int getAllExpenseTotal();
}
