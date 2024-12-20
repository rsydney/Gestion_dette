package com.ism.services;

import com.ism.entity.Client;
import com.ism.entity.DebtRequest;
import com.ism.enums.DebtRequestStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DebtRequestServiceImpl implements IDebtRequestService {

    private final List<DebtRequest> debtRequests = new ArrayList<>();

    @Override
    public void createDebtRequest(Client client, double amount, List<String> articles) {
        DebtRequest debtRequest = new DebtRequest();
        debtRequest.setStatus(DebtRequestStatus.PENDING);
        debtRequest.setClient(client);
        debtRequests.add(debtRequest);
        System.out.println("Demande de dette créée avec succès pour le client : " + client.getSurname());
    }

    @Override
    public List<DebtRequest> listDebtRequests(Client client, DebtRequestStatus status) {
        return debtRequests.stream()
                .filter(dr -> dr.getClient().equals(client) && (status == null || dr.getStatus() == status))
                .collect(Collectors.toList());
    }

    @Override
    public void sendReminderForCanceledRequest(Client client) {
        List<DebtRequest> canceledRequests = listDebtRequests(client, DebtRequestStatus.CANCELED);
        if (canceledRequests.isEmpty()) {
            System.out.println("Aucune demande annulée à relancer.");
        } else {
            for (DebtRequest request : canceledRequests) {
                System.out.println("Relance envoyée pour la demande annulée de montant : " + request.getAmount());
            }
        }
    }
}

