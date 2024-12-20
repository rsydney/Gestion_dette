package com.ism.services;


import java.util.List;

import com.ism.entity.Article;
import com.ism.entity.Client;
import com.ism.entity.Dette;
import com.ism.entity.User;
import com.ism.repository.ArticleRepository;
import com.ism.repository.DetteRepository;
import com.ism.repository.UserRepository;

public class UserServiceImpl implements UserServiceImplInter {
    private UserRepository userRepository;
    private ArticleRepository articleRepository;
    private DetteRepository detteRepository;

    public UserServiceImpl(UserRepository userRepository, ArticleRepository articleRepository, DetteRepository detteRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.detteRepository = detteRepository;
    }


    public UserServiceImpl() {
        //TODO Auto-generated constructor stub
    }


    @Override
    public User login(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Login ou mot de passe incorrect.");
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        if (!isValidRole(user.getRole())) {
            throw new IllegalArgumentException("Rôle non valide.");
        }
        userRepository.add(user);
    }

    @Override
    public void addClient(Client client) {
        userRepository.addClient(client);
    }

    @Override
    public void addDebt(Dette debt) {
        detteRepository.add(debt);
    }

    @Override
    public void createUserWithRole(String email, String login, String password, String role) {
        if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("BOUTIQUIER") || role.equalsIgnoreCase("CLIENT")) {
            User user = new User(email, login, password, role);
            userRepository.add(user);
            System.out.println("Compte " + role + " créé avec succès.");
        } else {
            System.out.println("Rôle non valide. Seuls ADMIN et BOUTIQUIER sont acceptés.");
        }
    }

    @Override
public void createUserForClientWithoutAccount(String clientSurname, String email, String login, String password) {
    System.out.println("Recherche du client avec le surname : " + clientSurname);
    
    // Recherche du client dans le repository
    Client client = userRepository.findClientBySurname(clientSurname);
    
    if (client == null) {
        // Client introuvable, création d'un nouveau client
        System.out.println("Aucun client trouvé avec le surname : " + clientSurname);
        System.out.println("Création d'un nouveau client...");
        
        client = new Client();
        client.setSurname(clientSurname); // On utilise le surname comme identifiant
        userRepository.addClient(client); // Ajout du nouveau client au repository
        System.out.println("Nouveau client créé : " + client);
    }
    
    if (client.getUser() != null) {
        // Le client est déjà associé à un compte
        System.out.println("Le client est déjà associé à un utilisateur : " + client.getUser());
        throw new IllegalArgumentException("Client déjà associé à un compte.");
    }
    
    // Création de l'utilisateur et association au client
    System.out.println("Création de l'utilisateur pour le client...");
    User user = new User(email, login, password, "CLIENT");
    client.setUser(user);
    userRepository.add(user); // Ajout de l'utilisateur au repository
    
    System.out.println("Utilisateur créé avec succès : " + user);
}



    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.getUsersByRole(role);
    }

    @Override
    public void toggleUserAccount(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            user.setActive(!user.isActive());
        } else {
            throw new IllegalArgumentException("Utilisateur non trouvé.");
        }
    }

    @Override
    public void createArticle(Article article) {
        articleRepository.add(article);
    }

    @Override
    public List<Article> getAvailableArticles() {
        return articleRepository.getAvailableArticles();
    }

    @Override
    public void archiveSettledDebts() {
        List<Dette> settledDebts = detteRepository.getSettledDebts();
        for (Dette debt : settledDebts) {
            debt.setStatus(false);
        }
    }

    @Override
    public List<User> getActiveUsers() {
        return userRepository.getActiveUsers();
    }

    @Override
    public void updateArticleStock(String articleName, int newQuantity) {
        Article article = articleRepository.findByName(articleName);
        if (article != null) {
            article.setQuantity(newQuantity);
        } else {
            throw new IllegalArgumentException("Article non trouvé.");
        }
    }

    @Override
    public void registerPayment(Client client, String paymentDate, double amount) {
        List<Dette> debts = userRepository.getUnpaidDebtsForUser(client);
        for (Dette debt : debts) {
            if (amount > 0 && amount <= debt.getAmountRemaining()) {
                debt.setAmountPaid(debt.getAmountPaid() + amount);
                debt.setAmountRemaining(debt.getAmountRemaining() - amount);
                debt.setPaymentDate(paymentDate);
                return;
            }
        }
        throw new IllegalArgumentException("Montant invalide ou client sans dettes non soldées.");
    }

    @Override
    public List<Client> getClients() {
        return userRepository.getAllClients();
    }

    @Override
    public Client findClientByPhone(String phone) {
        return userRepository.getAllClients().stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Dette> getUnpaidDebtsForClient(Client client) {
        return userRepository.getUnpaidDebtsForUser(client);
    }

    private boolean isValidRole(String role) {
        return role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("BOUTIQUIER") || role.equalsIgnoreCase("CLIENT");
    }
}
