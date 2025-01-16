package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;
    
    @GetMapping(value = "/viewItems")
    public String viewItems(Model model) {
        LocalDate now = LocalDate.now();
        model.addAttribute("year", now.getYear());
        model.addAttribute("month", now.getMonthValue());
        
        ArrayList<Expense> allItems = expenseService.getAllItems();
        model.addAttribute("items", allItems);
    
        int allIncomeTotal = expenseService.getAllIncomeTotal();
        model.addAttribute("allIncomeTotal", allIncomeTotal);
    
        int allExpenseTotal = expenseService.getAllExpenseTotal();
        model.addAttribute("allExpenseTotal", allExpenseTotal);
        
        model.addAttribute("allIncomeAllExpense", allExpenseTotal - allExpenseTotal);
        
        return "viewItems";
    }
    
    @GetMapping(value = "/addItem")
    public String addItem(Model model) {
        model.addAttribute("expenseItem", new Expense());
        return "addItem";
    }
    
    @PostMapping(value = "/addItem/new")
    public String addNewItem(@ModelAttribute("expenseItem") Expense expenseItem) {
        expenseService.saveItem(expenseItem);
        return "redirect:/viewItems";
    }
    
    @PostMapping(value = "/updateItem")
    public String updateItem(@RequestParam("selectedItems") ArrayList<Expense> selectedItems) {
        if (!ObjectUtils.isEmpty(selectedItems)) {
            expenseService.updateItems(selectedItems);
        }
        return "redirect:/viewItems";
    }
}
