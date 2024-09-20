package org.example.view;

import org.example.entities.Debt;
import org.example.services.DebtService;
import org.example.services.contract.DebtServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DebtView {

    private final DebtServiceI debtService;

    @Autowired
    public DebtView(DebtServiceI debtService) {
        this.debtService = debtService;
    }

    public void createDebtForClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'ID du client: ");
        int clientId = scanner.nextInt();

        System.out.print("Entrez le montant de la dette: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consomme le saut de ligne

        System.out.print("Entrez le statut de la dette: ");
        String status = scanner.nextLine();

        System.out.print("Entrez la date d'échéance (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        // Create and save the debt
        Debt debt = new Debt(0, clientId, amount, status, dueDate);
        debtService.save(debt);

        System.out.println("Dette ajoutée avec succès.");
    }

    public void listDebtsForClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'ID du client: ");
        long clientId = scanner.nextInt();

        debtService.findByClientId(clientId).forEach(System.out::println);
    }
}
