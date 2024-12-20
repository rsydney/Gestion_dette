package com.ism.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.ism.entity.Article;
import com.ism.entity.Client;
import com.ism.entity.Dette;
import com.ism.entity.User;
import com.ism.repository.core.Repository;
import com.ism.services.ArticleServiceImpl;
import com.ism.services.UserServiceImpl;

public class UserViews implements Repository<User> {
    private UserServiceImpl userServiceImpl;
    private User loggedInUser;
    ArticleServiceImpl articleServiceImpl = new ArticleServiceImpl(null);
    List<Article> listarticles=new ArrayList<>();

    public UserViews(UserServiceImpl userServiceImpl, User loggedInUser) {
        this.userServiceImpl = userServiceImpl;
        this.loggedInUser = loggedInUser;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- Menu Boutiquier ----");

        boolean running = true;
        while (running) {
            System.out.println("1. Créer un client");
            System.out.println("2. Lister les clients");
            System.out.println("3. Lister les dettes non soldées");
            System.out.println("4. Rechercher client par numéro de téléphone");
            System.out.println("5. Créer une dette");
            System.out.println("6. Enregistrer un paiement");
            System.out.println("7. Déconnexion");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createClient(scanner);
                    break;
                case 2:
                    listClients(scanner);
                    break;
                case 3:
                    listUnpaidDebts(scanner);
                    break;
                case 4:
                    listClientByPhone(scanner);
                    break;
                case 5:
                    createDebt(scanner);
                    break;
                case 6:
                    registerPayment(scanner);
                    break;
                case 7:
                    running = false;
                    System.out.println("Déconnexion réussie.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private void createClient(Scanner scanner) {
        System.out.print("Entrez le surname du client : ");
        String surname = scanner.next();
        System.out.print("Entrez le téléphone du client : ");
        String phone = scanner.next();
        System.out.print("Entrez l'adresse du client : ");
        String address = scanner.next();
        System.out.print("Souhaitez-vous créer un compte utilisateur ? (oui/non) : ");
        String createAccount = scanner.next();
    
        String email = null;
        String login = null;
        String password = null;
    
        if (createAccount.equalsIgnoreCase("oui")) {
            System.out.print("Entrez l'email du client : ");
            email = scanner.next();
            System.out.print("Entrez le login du client : ");
            login = scanner.next();
            System.out.print("Entrez le mot de passe du client : ");
            password = scanner.next();
        }
    
        
        Client newClient = new Client(surname, phone, address, email, login, password);
        userServiceImpl.addClient(newClient); 
        System.out.println("Client créé avec succès : " + surname);
    }
    

    private void listClients(Scanner scanner) {
        System.out.println("Voulez-vous filtrer par compte utilisateur ? (oui/non) : ");
        String filterChoice = scanner.next().toLowerCase();
        
        List<Client> clients;
        if (filterChoice.equals("oui")) {
            System.out.println("Voulez-vous lister les clients avec un compte utilisateur (oui) ou sans compte (non) ?");
            String accountChoice = scanner.next().toLowerCase();
            
            if (accountChoice.equals("oui")) {
                clients = userServiceImpl.getClients().stream()
                        .filter(client -> client.getAccount() != null)
                        .toList();
            } else {
                clients = userServiceImpl.getClients().stream()
                        .filter(client -> client.getAccount() == null)
                        .toList();
            }
        } else {
            clients = userServiceImpl.getClients();
        }
    
        System.out.println("Liste des clients : ");
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            clients.forEach(System.out::println);
        }
    }

    private void listClientByPhone(Scanner scanner) {
        System.out.print("Entrez le numéro de téléphone du client : ");
        String phone = scanner.next();
        Client client = userServiceImpl.findClientByPhone(phone);

        if (client != null) {
            System.out.println("Informations du client : " + client);
        } else {
            System.out.println("Client non trouvé avec le numéro de téléphone : " + phone);
        }
    }

    private void listUnpaidDebts(Scanner scanner) {
        System.out.print("Entrez le numéro de téléphone du client : ");
        String phone = scanner.next();
        
        Client client = userServiceImpl.findClientByPhone(phone);
        if (client == null) {
            System.out.println("Client non trouvé.");
            return;
        }
    
        List<Dette> unpaidDebts = userServiceImpl.getUnpaidDebtsForClient(client).stream()
                .filter(debt -> debt.getAmountRemaining() > 0)
                .toList();
    
        if (unpaidDebts.isEmpty()) {
            System.out.println("Aucune dette non soldée pour ce client.");
            return;
        }
    
        System.out.println("Dettes non soldées pour le client " + client.getSurname() + " :");
        for (int i = 0; i < unpaidDebts.size(); i++) {
            Dette debt = unpaidDebts.get(i);
            System.out.println((i + 1) + ". Dette du " + debt.getDate() + " - Montant restant : " + debt.getAmountRemaining());
        }
    }

    private void registerPayment(Scanner scanner) {
        System.out.print("Entrez le numéro de téléphone du client : ");
        String phone = scanner.next();

        Client client = userServiceImpl.findClientByPhone(phone);
        if (client == null) {
            System.out.println("Client non trouvé.");
            return;
        }

        List<Dette> unpaidDebts = userServiceImpl.getUnpaidDebtsForClient(client).stream()
                .filter(debt -> debt.getAmountRemaining() > 0)
                .toList();

        if (unpaidDebts.isEmpty()) {
            System.out.println("Le client n'a aucune dette non soldée.");
            return;
        }

        System.out.println("Dettes non soldées pour le client " + client.getSurname() + " :");
        for (int i = 0; i < unpaidDebts.size(); i++) {
            Dette debt = unpaidDebts.get(i);
            System.out.println((i + 1) + ". Dette du " + debt.getDate() + " - Montant restant : " + debt.getAmountRemaining());
        }

        System.out.print("Choisissez une dette pour enregistrer un paiement (1-" + unpaidDebts.size() + ") : ");
        int debtChoice = scanner.nextInt();

        if (debtChoice < 1 || debtChoice > unpaidDebts.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Dette selectedDebt = unpaidDebts.get(debtChoice - 1);

        System.out.print("Entrez le montant du paiement : ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= selectedDebt.getAmountRemaining()) {
            selectedDebt.setAmountPaid(selectedDebt.getAmountPaid() + amount);
            selectedDebt.setAmountRemaining(selectedDebt.getAmountRemaining() - amount);
            selectedDebt.setPaymentDate(java.time.LocalDate.now().toString());

            System.out.println("Paiement enregistré avec succès. Montant payé : " + amount);
            System.out.println("Montant restant à payer : " + selectedDebt.getAmountRemaining());
        } else {
            System.out.println("Montant du paiement invalide. Il doit être inférieur ou égal au montant restant.");
        }
    }

    private void createDebt(Scanner scanner) {
        System.out.print("Entrez la date de la dette (format : yyyy-mm-dd) : ");
        String date = scanner.next(); 
        
        System.out.print("Entrez le montant de la dette : ");
        double amount = scanner.nextDouble();
        
        System.out.print("Entrez le montant versé : ");
        double amountPaid = scanner.nextDouble();
        
        System.out.print("Entrez le montant restant : ");
        double amountRemaining = scanner.nextDouble();
        
        System.out.print("Entrez les articles associés à la dette (séparés par une virgule) : ");
        List<Article> articles = articleServiceImpl.findAll();
        int articlesInput = scanner.nextInt();
        Optional<Article> article = articleServiceImpl.findById(articlesInput);
        article.ifPresent(listarticles::add);


    
        System.out.print("Entrez le numéro de téléphone du client : ");
        String clientPhone = scanner.next();
        Client client = userServiceImpl.findClientByPhone(clientPhone);
    
        if (client != null) {
            
            Dette debt = new Dette(date, amount, amountPaid, amountRemaining, articles, false, client);
            debt.setArticles(listarticles);
            userServiceImpl.addDebt(debt);
            System.out.println("Dette créée avec succès : " + debt);
        } else {
            System.out.println("Client non trouvé avec le numéro de téléphone : " + clientPhone);
        }
    }


    @Override
    public void add(User entity) {
        
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public List<User> getAll() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<User> findById(int id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<User> findBy(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    
}

