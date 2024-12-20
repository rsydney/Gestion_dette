package com.ism.repository;

import com.ism.entity.User;
import com.ism.repository.core.Repository;
import com.ism.entity.Client;
import com.ism.entity.Dette;

import java.util.List;

public interface UserRepositoryInter extends Repository<User> {
    User findByLogin(String login);
    List<User> getUsersByRole(String role);
    List<User> getActiveUsers();
    List<Dette> getUnpaidDebtsForUser(Client client);
    List<Client> getClients();
}
