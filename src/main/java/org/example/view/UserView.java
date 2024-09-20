package org.example.view;

import org.example.entities.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserView {

    private final UserService userService;

    @Autowired
    public UserView(UserService userService) {
        this.userService = userService;
    }

    public void createUserForClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le nom d'utilisateur: ");
        String username = scanner.nextLine();

        System.out.print("Entrez le mot de passe: ");
        String password = scanner.nextLine();

        System.out.print("Entrez le rôle de l'utilisateur: ");
        String role = scanner.nextLine();

        // Create and save the user
        User user = new User(0, username, password, role);
        userService.save(user);

        System.out.println("Compte utilisateur créé avec succès.");
    }
}
