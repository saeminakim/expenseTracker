package com.example.expensetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private int income;
    private int expense;
    private String memo;
    private String tag;
    
    public Long getId () {
        return id;
    }
    
    public void setId (Long id) {
        this.id = id;
    }
    
    public String getSubject () {
        return subject;
    }
    
    public void setSubject (String subject) {
        this.subject = subject;
    }
    
    public int getIncome () {
        return income;
    }
    
    public void setIncome (int income) {
        this.income = income;
    }
    
    public int getExpense () {
        return expense;
    }
    
    public void setExpense (int expense) {
        this.expense = expense;
    }
    
    public String getMemo () {
        return memo;
    }
    
    public void setMemo (String memo) {
        this.memo = memo;
    }
    
    public String getTag () {
        return tag;
    }
    
    public void setTag (String tag) {
        this.tag = tag;
    }
}
