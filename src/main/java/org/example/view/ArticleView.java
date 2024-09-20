//package org.example.view;
//
//import org.example.entities.Article;
//import org.example.services.ArticleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;








//
//import java.util.Scanner;
//
//@Component
//public class ArticleView {
//
//    private final ArticleService articleService;
//
//    @Autowired
//    public ArticleView(ArticleService articleService) {
//        this.articleService = articleService;
//    }
//
//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        String continueInput;
//
//        do {
//            System.out.println("Enter article label: ");
//            String label = scanner.nextLine();
//
//            System.out.println("Enter article quantity: ");
//            int quantity = scanner.nextInt();
//
//            System.out.println("Enter article category: ");
//            scanner.nextLine(); // Consume the newline left-over
//            String category = scanner.nextLine();
//
//            // Create an Article object
//            Article article = new Article(0, label, quantity, category);
//
//            // Save the article using the service
//            articleService.save(article);
//
//            System.out.println("Article saved successfully!");
//            System.out.println("///////////////All articles://////////////");
//            articleService.findAll().forEach(System.out::println);
//            System.out.println("Do you want to add another article? (yes/no): ");
//            continueInput = scanner.nextLine();
//
//        } while (continueInput.equalsIgnoreCase("yes"));
//
//        System.out.println("Exiting...");
//    }
//}
package org.example.view;

import org.example.entities.Article;
import org.example.services.ArticleService;
import org.example.services.contract.ArticleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ArticleView {

    private final ArticleServiceI articleService;

    @Autowired
    public ArticleView(ArticleServiceI articleService) {
        this.articleService = articleService;
    }

    public void addArticle() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le titre de l'article: ");
        String title = scanner.nextLine();

        System.out.print("Entrez la quantité: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consomme le saut de ligne

        System.out.print("Entrez la catégorie: ");
        String category = scanner.nextLine();

        // Create and save the article
        Article article = new Article(0, title, quantity, category);
        articleService.save(article);

        System.out.println("Article ajouté avec succès.");
    }

    public void listArticles() {
        articleService.findAll().forEach(System.out::println);
    }
}
