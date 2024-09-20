package org.example.view;

import java.util.Scanner;

public class MainView {

    private final ClientView clientView;
    private final UserView userView;
    private final DebtView debtView;
    private final PaymentView paymentView;
    private final ArticleView articleView;  // Ajout de la gestion des articles

    public MainView(ClientView clientView, UserView userView, DebtView debtView, PaymentView paymentView, ArticleView articleView) {
        this.clientView = clientView;
        this.userView = userView;
        this.debtView = debtView;
        this.paymentView = paymentView;
        this.articleView = articleView;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Menu Projet ---");
            System.out.println("1. Ajouter Client");
            System.out.println("2. Lister Client");
            System.out.println("3. Créer un compte user pour un client");
            System.out.println("4. Créer une Dette pour un Client");
            System.out.println("5. Effectuer un Paiement");
            System.out.println("6. Lister les dettes d'un client");
            System.out.println("7. Lister les Paiements pour une dette");
            System.out.println("8. Ajouter un Article");
            System.out.println("9. Lister les Articles");
            System.out.println("10. Quitter");

            System.out.print("\nVeuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    clientView.addClient();
                    break;
                case 2:
                    clientView.listClients();
                    break;
                case 3:
                    userView.createUserForClient();
                    break;
                case 4:
                    debtView.createDebtForClient();
                    break;
                case 5:
                    paymentView.makePayment();
                    break;
                case 6:
                    debtView.listDebtsForClient();
                    break;
                case 7:
                    paymentView.listPaymentsForDebt();
                    break;
                case 8:
                    articleView.addArticle();
                    break;
                case 9:
                    articleView.listArticles();
                    break;
                case 10:
                    System.out.println("Quitter le programme...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

        } while (choice != 10);

        scanner.close();
    }
}
