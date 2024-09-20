package org.example.view;

import org.example.entities.Client;
import org.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ClientView {

    private final ClientService clientService;

    @Autowired
    public ClientView(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le nom du client: ");
        String name = scanner.nextLine();

        System.out.print("Entrez le numéro de téléphone: ");
        String phone = scanner.nextLine();

        System.out.print("Entrez l'email: ");
        String email = scanner.nextLine();

        System.out.print("Entrez l'ID de l'utilisateur associé: ");
        int userId = scanner.nextInt();

        // Create and save the client
        Client client = new Client(0, name, phone, email, userId);
        clientService.save(client);

        System.out.println("Client ajouté avec succès.");
    }

    public void listClients() {
        clientService.findAll().forEach(System.out::println);
    }
}
