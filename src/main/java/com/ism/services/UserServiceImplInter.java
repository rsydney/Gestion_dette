package com.ism.services;

import com.ism.entity.User;
import com.ism.entity.Client;
import com.ism.entity.Article;
import com.ism.entity.Dette;

import java.util.List;

public interface UserServiceImplInter {
    User login(String login, String password);
    void addUser(User user);
    void addClient(Client client);
    void addDebt(Dette debt);
    void createUserWithRole(String email, String login, String password, String role);
    void createUserForClientWithoutAccount(String clientSurname, String email, String login, String password);
    List<User> getUsersByRole(String role);
    void toggleUserAccount(String login);
    void createArticle(Article article);
    List<Article> getAvailableArticles();
    void archiveSettledDebts();
    List<User> getActiveUsers();
    void updateArticleStock(String articleName, int newQuantity);
    void registerPayment(Client client, String paymentDate, double amount);
    List<Client> getClients();
    List<Dette> getUnpaidDebtsForClient(Client client);
    Client findClientByPhone(String phone);
}
