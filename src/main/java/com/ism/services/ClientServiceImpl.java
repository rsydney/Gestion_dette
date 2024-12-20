package com.ism.services;

import java.util.List;
import java.util.Optional;

import com.ism.entity.Client;

public class ClientServiceImpl implements ClientServiceImplInter {
    private List<Client> clients;

    public ClientServiceImpl(List<Client> clients) {
        this.clients = clients;
    }

    public ClientServiceImpl() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public void create(Client client) {
        clients.add(client);
    }

    @Override
    public Optional<Client> findById(String phone) {
        return clients.stream()
                      .filter(client -> client.getPhone().equals(phone))
                      .findFirst();
    }

    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public void update(Client client) {
        // Implémentation de la mise à jour
    }

    @Override
    public void delete(Client client) {
        clients.remove(client);
    }

    @Override
    public List<Client> filterClientsWithAccount() {
        return clients.stream()
                      .filter(client -> client.getAccount() != null)
                      .toList();
    }
}