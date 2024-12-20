package com.ism.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dette {
    private String id;
    private String date; 
    private double amount; 
    private double amountPaid; 
    private double amountRemaining; 
    private List<Article> articles; 
    private boolean status; 
    private Client client; 
    private String paymentDate;

    public Dette(String id, String date, double amount, double amountPaid, double amountRemaining, List<Article> articles, boolean status, Client client) {
        this.id = id; 
        this.date = date;
        this.amount = amount;
        this.amountPaid = amountPaid;
        this.amountRemaining = amountRemaining;
        this.articles = articles;
        this.status = status;
        this.client = client;
        this.paymentDate = null; 
    }
    public Dette(String date, double amount, double amountPaid, double amountRemaining, List<Article> articles, boolean status, Client client) {
        this.date = date;
        this.amount = amount;
        this.amountPaid = amountPaid;
        this.amountRemaining = amountRemaining;
        this.articles = articles;
        this.status = status;
        this.client = client;
    }
}


