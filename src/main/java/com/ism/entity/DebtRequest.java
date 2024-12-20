package com.ism.entity;

import com.ism.enums.DebtRequestStatus;

import java.util.List;

public class DebtRequest {
    private Client client;
    private double amount;
    private List<String> articles; // Articles associés à la dette
    private DebtRequestStatus status;

    public DebtRequest(Client client, double amount, List<String> articles) {
        this.client = client;
        this.amount = amount;
        this.articles = articles;
        this.status = DebtRequestStatus.PENDING; // Statut initial
    }

    public DebtRequest() {
        //TODO Auto-generated constructor stub
    }

    // Getters et Setters
    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    public List<String> getArticles() {
        return articles;
    }

    public DebtRequestStatus getStatus() {
        return status;
    }

    public void setStatus(DebtRequestStatus status) {
        this.status = status;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

