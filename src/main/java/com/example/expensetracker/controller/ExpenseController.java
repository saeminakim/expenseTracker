package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;

    @GetMapping(value = "/")
    public String welcome(Model model) {
        LocalDate now = LocalDate.now();
        model.addAttribute("year", now.getYear());
        model.addAttribute("month", now.getMonthValue());

        ArrayList<Expense> itemsByMonth = expenseService.findByMonthAndYear(now.getYear(), now.getMonthValue());
        if (ObjectUtils.isEmpty(itemsByMonth)) {
            return "index";
        }

        model.addAttribute("items", itemsByMonth);

        // TODO month별 조회했을 때 합계
        return "viewItems";
    }
    
    @GetMapping(value = "/viewItems")
    public String viewAllItems(Model model) {
        LocalDate now = LocalDate.now();
        model.addAttribute("year", now.getYear());
        model.addAttribute("month", now.getMonthValue());
        
        ArrayList<Expense> allItems = expenseService.getAllItems();
        if (ObjectUtils.isEmpty(allItems)) {
            return "index";
        }
        model.addAttribute("items", allItems);
    
        int allIncomeTotal = expenseService.getAllIncomeTotal();
        model.addAttribute("allIncomeTotal", allIncomeTotal);
    
        int allExpenseTotal = expenseService.getAllExpenseTotal();
        model.addAttribute("allExpenseTotal", allExpenseTotal);
        model.addAttribute("allIncomeAllExpense", allIncomeTotal - allExpenseTotal);
        
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
    
    @PostMapping(value = "/processItem")
    public String processItem(@RequestParam(name = "selectedItems", required = false) ArrayList<Expense> selectedItems
            , @RequestParam("action") String action, Model model) {
        if (!ObjectUtils.isEmpty(selectedItems)) {
            switch (action) {
                case "update":
                    Optional<Expense> item = expenseService.findById(selectedItems.get(0).getId());
                    model.addAttribute("expenseItem", item);
                    return "updateItem";
                case "delete":
                    expenseService.deleteItems(selectedItems);
                    break;
                default:
                    break;
            }
        } else {
            model.addAttribute("expenseItem", new Expense());
            return "addItem";
        }
        return "redirect:/viewItems";
    }

    @PostMapping(value = "/updateItem")
    public String updateItem(@ModelAttribute("expenseItem") Expense expenseItem) {
        expenseService.saveItem(expenseItem);
        return "redirect:/viewItems";
    }
}
