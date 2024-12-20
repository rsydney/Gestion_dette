package com.ism.views;

import com.ism.entity.User;
import com.ism.services.UserServiceImpl;

import java.util.Scanner;

public class LoginView {
    private UserServiceImpl userServiceImpl;

    public LoginView(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public User display() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- Connexion ----");
        System.out.println("admin-0000 / boutiquier-1111 / client-");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userServiceImpl.login(login, password);

        if (user != null) {
            System.out.println("Connexion réussie. Rôle: " + user.getRole());
            return user;
        } else {
            System.out.println("Login ou mot de passe incorrect.");
            return null; 
        }
    }
}
