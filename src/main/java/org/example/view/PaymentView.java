package org.example.view;

import org.example.entities.Payment;
import org.example.services.PaymentService;
import org.example.services.contract.PaymentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PaymentView {

    private final PaymentServiceI paymentService;

    @Autowired
    public PaymentView(PaymentServiceI paymentService) {
        this.paymentService = paymentService;
    }

    public void makePayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'ID de la dette: ");
        int debtId = scanner.nextInt();

        System.out.print("Entrez le montant du paiement: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez la date du paiement (YYYY-MM-DD): ");
        String paymentDate = scanner.nextLine();

        Payment payment = new Payment(0, debtId, amount, paymentDate);
        paymentService.save(payment);

        System.out.println("Paiement effectué avec succès.");
    }

    public void listPaymentsForDebt() {


        paymentService.findAll().forEach(System.out::println);
    }
}
