package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;
    
    @GetMapping(value = "/viewItems")
    public String viewItems(Model model) {
        ArrayList<Expense> allItems = expenseService.getAllItems();
        model.addAttribute("items", allItems);
    
        LocalDate now = LocalDate.now();
        model.addAttribute("year", now.getYear());
        model.addAttribute("month", now.getMonthValue());
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
}
