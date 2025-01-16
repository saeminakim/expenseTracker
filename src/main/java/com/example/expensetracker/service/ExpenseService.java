package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    public void saveItem(Expense item) {
        expenseRepository.save(item);
    }
    
    public void updateItems(ArrayList<Expense> selectedItems) {
        expenseRepository.saveAll(selectedItems);
    }
    
    public ArrayList<Expense> getAllItems () {
        return expenseRepository.findAll();
    }
    
    public int getAllIncomeTotal() {
        return expenseRepository.getAllIncomeTotal();
    }
    
    public int getAllExpenseTotal() {
        return expenseRepository.getAllExpenseTotal();
    }
}
