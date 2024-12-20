package com.ism.repository;

import com.ism.entity.Client;
import com.ism.repository.core.Repository;

import java.util.List;

public interface ClientRepositoryInter extends Repository<Client> {
    List<Client> getClientsWithAccount();
    List<Client> getClientsWithoutAccount();
}
