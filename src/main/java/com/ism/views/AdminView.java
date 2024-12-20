package com.ism.views;

import com.ism.entity.User;
import com.ism.entity.Article;
import com.ism.repository.core.ServiceRepository;
import com.ism.services.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private UserServiceImpl userServiceImpl;
    private User loggedInUser; 
    
    public AdminView(UserServiceImpl userServiceImpl, User loggedInUser) {
        this.userServiceImpl = userServiceImpl;
        this.loggedInUser = loggedInUser;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("---- Menu Admin ----");
            System.out.println("1. Créer un compte utilisateur");
            System.out.println("2. Désactiver/Activer un compte utilisateur");
            System.out.println("3. Créer ou lister des articles");
            System.out.println("4. Archiver les dettes soldées");
            System.out.println("5. Afficher les comptes utilisateurs actifs ou par rôle");
            System.out.println("6. Mettre à jour la quantité en stock (article)");
            System.out.println("7. Quitter");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    toggleUserAccount(scanner);
                    break;
                case 3:
                    manageArticles(scanner);
                    break;
                case 4:
                    archiveSettledDebts();
                    break;
                case 5:
                    displayUserAccountsByStatusOrRole(scanner);
                    break;
                case 6:
                    updateArticleStock(scanner);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void createAccount(Scanner scanner) {
        System.out.println("Créer un compte pour : 1. Client sans compte | 2. Utilisateur avec rôle spécifique");
        int accountType = scanner.nextInt();
        scanner.nextLine();

        if (accountType == 1) {
            System.out.println("Entrez le surname du client :");
            String phone = scanner.nextLine();
            System.out.println("Entrez l'email du client : ");
            String email = scanner.nextLine();
            System.out.println("Entrez le login : ");
            String login = scanner.nextLine();
            System.out.println("Entrez le mot de passe : ");
            String password = scanner.nextLine();

            userServiceImpl.createUserForClientWithoutAccount(phone, email, login, password);
            System.out.println("Compte utilisateur créé pour le client.");
        } else if (accountType == 2) {
            System.out.println("Entrez l'email : ");
            String email = scanner.nextLine();
            System.out.println("Entrez le login : ");
            String login = scanner.nextLine();
            System.out.println("Entrez le mot de passe : ");
            String password = scanner.nextLine();
            System.out.println("Entrez le rôle (Admin/Boutiquier) : ");
            String role = scanner.nextLine();

            // Vérification du rôle valide
            if (!role.equalsIgnoreCase("Admin") && !role.equalsIgnoreCase("Boutiquier")) {
                System.out.println("Rôle invalide. Veuillez entrer Admin ou Boutiquier.");
                return;
            }

            userServiceImpl.createUserWithRole(email, login, password, role);
            System.out.println("Compte utilisateur avec rôle " + role + " créé.");
        } else {
            System.out.println("Option invalide.");
        }
    }

    private void toggleUserAccount(Scanner scanner) {
        System.out.println("Entrez le login de l'utilisateur à activer/désactiver :");
        String login = scanner.next();
        userServiceImpl.toggleUserAccount(login);
        System.out.println("Le statut du compte utilisateur a été modifié.");
    }

    private void manageArticles(Scanner scanner) {
        System.out.println("1. Créer un article | 2. Lister les articles disponibles");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consomme la ligne restante

        if (choice == 1) {
            System.out.println("Entrez le nom de l'article : ");
            String name = scanner.nextLine();
            System.out.println("Entrez la quantité en stock : ");
            int stock = scanner.nextInt();
            scanner.nextLine(); // Consomme la ligne restante après nextInt()
            userServiceImpl.createArticle(new Article(name, stock));
            System.out.println("Article créé.");
        } else if (choice == 2) {
            List<Article> availableArticles = userServiceImpl.getAvailableArticles();
            System.out.println("Articles disponibles :");
            availableArticles.forEach(article -> System.out.println(article.getName() + " (Stock : " + article.getQuantity() + ")"));
        } else {
            System.out.println("Option invalide.");
        }
    }

    private void archiveSettledDebts() {
        userServiceImpl.archiveSettledDebts();
        System.out.println("Les dettes soldées ont été archivées.");
    }

    private void displayUserAccountsByStatusOrRole(Scanner scanner) {
        System.out.println("1. Afficher les comptes actifs | 2. Filtrer par rôle");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consomme la ligne restante

        if (choice == 1) {
            List<User> activeUsers = userServiceImpl.getActiveUsers();
            System.out.println("Comptes utilisateurs actifs :");
            activeUsers.forEach(user -> System.out.println(user.getLogin() + " (Rôle : " + user.getRole() + ")"));
        } else if (choice == 2) {
            System.out.println("Entrez le rôle à filtrer (Admin/Boutiquier) : ");
            String role = scanner.nextLine();
            List<User> usersByRole = userServiceImpl.getUsersByRole(role);
            System.out.println("Utilisateurs avec le rôle " + role + " :");
            usersByRole.forEach(user -> System.out.println(user.getLogin()));
        } else {
            System.out.println("Option invalide.");
        }
    }

    private void updateArticleStock(Scanner scanner) {
        System.out.println("Entrez le nom de l'article : ");
        String name = scanner.next();
        System.out.println("Entrez la nouvelle quantité en stock : ");
        int newStock = scanner.nextInt();
        userServiceImpl.updateArticleStock(name, newStock);
        System.out.println("Quantité mise à jour.");
    }
}
