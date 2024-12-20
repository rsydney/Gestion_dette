package com.ism.services;

import com.ism.entity.Client;
import com.ism.entity.DebtRequest;
import com.ism.enums.DebtRequestStatus;

import java.util.List;

public interface IDebtRequestService {
    void createDebtRequest(Client client, double amount, List<String> articles);
    List<DebtRequest> listDebtRequests(Client client, DebtRequestStatus status);
    void sendReminderForCanceledRequest(Client client);
}

