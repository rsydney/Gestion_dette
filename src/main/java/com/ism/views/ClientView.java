package com.ism.views;

import com.ism.entity.Client;
import com.ism.entity.DebtRequest;
import com.ism.entity.User;
import com.ism.enums.DebtRequestStatus;
import com.ism.services.DebtRequestServiceImpl;
import com.ism.services.IDebtRequestService;
import com.ism.services.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ClientView {

    private UserServiceImpl userServiceImpl;
    private User loggedInUser;
    private DebtRequestServiceImpl debtRequestServiceImpl = new DebtRequestServiceImpl();
    private final Scanner scanner = new Scanner(System.in);
    


    public ClientView(DebtRequestServiceImpl debtRequestService, UserServiceImpl userServiceImpl, User loggedInUser) {
        this.debtRequestServiceImpl = debtRequestServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.loggedInUser = loggedInUser;
        
    }




    public ClientView() {
        //TODO Auto-generated constructor stub
    }




    public ClientView(DebtRequest instanceDebtRequest, UserServiceImpl instanceUserServiceImpl, User loggedInUser2) {
        //TODO Auto-generated constructor stub
    }




    public void display(Client client) {
        boolean running = true;
        while (running) {
            System.out.println("=== Menu Client ===");
            System.out.println("1. Créer une demande de dette");
            System.out.println("2. Lister mes demandes de dette");
            System.out.println("3. Relancer une demande annulée");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> createDebtRequest(client);
                case 2 -> listDebtRequests(client);
                case 3 -> sendReminder(client);
                case 0 -> running = false;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private void createDebtRequest(Client client) {
        System.out.print("Entrez le montant : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        debtRequestServiceImpl.createDebtRequest(client, amount, null); // Articles peuvent être ajoutés ici
    }
    
    private void listDebtRequests(Client client) {
        System.out.println("1. Toutes les demandes");
        System.out.println("2. Filtrer par état (1. En cours, 2. Annulée)");
        int filter = scanner.nextInt();
        scanner.nextLine();

        DebtRequestStatus status = switch (filter) {
            case 1 -> DebtRequestStatus.PENDING;
            case 2 -> DebtRequestStatus.CANCELED;
            default -> null;
        };

        List<DebtRequest> requests = debtRequestServiceImpl.listDebtRequests(client, status);
        if (requests.isEmpty()) {
            System.out.println("Aucune demande trouvée.");
        } else {
            requests.forEach(r -> System.out.println("- Montant : " + r.getAmount() + ", État : " + r.getStatus()));
        }
    }

    private void sendReminder(Client client) {
        debtRequestServiceImpl.sendReminderForCanceledRequest(client);
    }

    public void display() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}



