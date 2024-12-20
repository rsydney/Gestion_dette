package com.ism;

import java.util.ArrayList;
import java.util.List;

import com.core.FactoryService;
import com.core.FactoryViews;
import com.ism.entity.Article;
import com.ism.entity.Client;
import com.ism.entity.DebtRequest;
import com.ism.entity.Dette;
import com.ism.entity.User;
import com.ism.repository.ArticleRepository;
import com.ism.repository.ClientRepository;
import com.ism.repository.DetteRepository;
import com.ism.repository.UserRepository;
import com.ism.services.DebtRequestServiceImpl;
import com.ism.services.UserServiceImpl;
import com.ism.views.LoginView;
import com.ism.views.UserViews;
import com.ism.views.AdminView;
import com.ism.views.ClientView;
import com.ism.repository.core.*;

public class Main {
    public static void main(String[] args) {

        FactoryService factoryService=new FactoryService();

        User user = new User("admin@example.com", "admin", "0000","ADMIN");
        User user2 = new User("boutiquier@example.com", "boutiquier", "1111","BOUTIQUIER");
        User user3 = new User("client@example.com", "client", "","CLIENT");
        Client client = new Client(1, "rs", "777","dakar");


        
        factoryService.getInstanceUserServiceImpl().addUser(user);
        factoryService.getInstanceUserServiceImpl().addUser(user2);
        factoryService.getInstanceUserServiceImpl().addUser(user3);


        LoginView loginView = new LoginView(factoryService.getInstanceUserServiceImpl());
        User loggedInUser = loginView.display(); 

        if (loggedInUser != null) {
            if ("ADMIN".equals(loggedInUser.getRole())) {
                AdminView adminView = new AdminView(factoryService.getInstanceUserServiceImpl(), loggedInUser);
                adminView.display();
            } else if ("BOUTIQUIER".equals(loggedInUser.getRole())) {
                UserViews userView = new UserViews(factoryService.getInstanceUserServiceImpl(), loggedInUser);
                userView.display();
            }  else if ("CLIENT".equals(loggedInUser.getRole())) {
                ClientView clientView = new ClientView(factoryService.getInstanceDebtRequest(), factoryService.getInstanceUserServiceImpl(), loggedInUser);
                clientView.display(client);
            } else {
                System.out.println("Rôle inconnu. Veuillez contacter l'administrateur.");
            }
        } else {
            System.out.println("Échec de la connexion.");
        }
    }
}



