package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    public void saveItem(Expense item) {
        expenseRepository.save(item);
    }

    public void deleteItems(ArrayList<Expense> selectedItems) {
        List<Long> idList = selectedItems.stream().map(Expense::getId).collect(Collectors.toList());
        expenseRepository.deleteAllById(idList);
    }
    
    public ArrayList<Expense> getAllItems () {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public ArrayList<Expense> findByMonthAndYear(int year, int month) {
        return expenseRepository.findByMonthAndYear(year, month);
    }
    
    public int getAllIncomeTotal() {
        return expenseRepository.getAllIncomeTotal();
    }
    
    public int getAllExpenseTotal() {
        return expenseRepository.getAllExpenseTotal();
    }
}
