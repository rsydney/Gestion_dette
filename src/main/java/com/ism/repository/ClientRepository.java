package com.ism.repository;

import com.ism.entity.Client;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientRepository implements ClientRepositoryInter {
    private List<Client> clients;

    public ClientRepository(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void add(Client client) {
        clients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public Optional<Client> findBy(String phone) {
        return clients.stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst();
    }

    @Override
    public List<Client> getClientsWithAccount() {
        return clients.stream()
                .filter(client -> client.getAccount() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> getClientsWithoutAccount() {
        return clients.stream()
                .filter(client -> client.getAccount() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
