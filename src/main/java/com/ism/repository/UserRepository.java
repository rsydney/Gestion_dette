package com.ism.repository;

import com.ism.entity.Client;
import com.ism.entity.Dette;
import com.ism.entity.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository implements UserRepositoryInter {
    private List<User> users; // Pas d'initialisation directe
    private List<Client> clients; // Liste des clients
    private List<Dette> debts; // Liste des dettes

    // Injection des listes via le constructeur
    public UserRepository() {
        this.users = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.debts = new ArrayList<>();
    }
    


    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public Optional<User> findBy(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    public User findByLogin(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public Client findClientBySurname(String surname) {
        return clients.stream()
                .filter(client -> client.getSurname().equalsIgnoreCase(surname))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUsersByRole(String role) {
        return users.stream()
                .filter(user -> user.getRole().equals(role))
                .collect(Collectors.toList());
    }
  /*   public void addUser(User user) {
        users.add(user);
    }  */
    public List<User> getActiveUsers() {
        return users.stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    public List<Dette> getUnpaidDebtsForUser(Client client) {
        return debts.stream() // Utilisation de la liste debts
                .filter(debt -> debt.getClient() != null && debt.getClient().equals(client))
                .filter(debt -> debt.getAmountRemaining() > 0)
                .collect(Collectors.toList());
    }
    public List<Client> getClients() {
        return clients;
    }
    public List<Client> getAllClients() {
    return new ArrayList<>(clients); 
}

    @Override
    public Optional<User> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
