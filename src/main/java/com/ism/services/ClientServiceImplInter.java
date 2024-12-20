package com.ism.services;

import com.ism.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceImplInter {
    void create(Client client);
    Optional<Client> findById(String phone);
    List<Client> findAll();
    void update(Client client);
    void delete(Client client);
    List<Client> filterClientsWithAccount();
}
