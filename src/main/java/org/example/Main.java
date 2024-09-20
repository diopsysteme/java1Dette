package org.example;

import org.example.config.AppConfig;
import org.example.entities.Article;
import org.example.services.ArticleService;
import org.example.view.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ClientView clientView = context.getBean(ClientView.class);
        UserView userView = context.getBean(UserView.class);
        DebtView debtView = context.getBean(DebtView.class);
        PaymentView paymentView = context.getBean(PaymentView.class);
        ArticleView articleView = context.getBean(ArticleView.class);

        MainView mainView = new MainView(clientView, userView, debtView, paymentView, articleView);

        // Exécuter le menu principal
        mainView.showMenu();
//
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        ArticleView articleView = context.getBean(ArticleView.class);
//
//        // Run the view to interact with the user
//        articleView.run();
//        articleService.save(new Article(1, "Java Basics",12,"ddd"));
//        articleService.save(new Article(2, "Spring Framework",33,"dd"));
//        articleService.save(new Article(3, "Hibernate",333,"zzz"));
//
//        System.out.println("All articles:");
//        articleService.findAll().forEach(System.out::println);
//
//        List<Article> foundArticles = articleService.findByTitle("Java Basics");
//        System.out.println("Found articles with title 'Java Basics':");
//        foundArticles.forEach(System.out::println);
//
//        articleService.find(1L).ifPresent(article ->
//                System.out.println("Found article with ID 1: " + article)
//        );

//        Article updatedArticle = new Article(1L, "Advanced Java");
//        articleService.update(updatedArticle);
//        System.out.println("After update of article with ID 1:");
//        articleService.findAll().forEach(System.out::println);

//        articleService.delete(2L);
//        System.out.println("After deletion of article with ID 2:");
//        articleService.findAll().forEach(System.out::println);
//    }

//            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//            ArticleServiceI articleService = context.getBean(ArticleServiceI.class);
//
//            Article newArticle = new Article();
//            newArticle.setTitle("Dependency Injection with Spring");
//            newArticle.setQuantity(200);
//            newArticle.setCategory("Dependency Injection");
//            newArticle.setId(1);
//            articleService.save(newArticle);
//
//            articleService.findAll().forEach(article -> {
//                System.out.println("Article Title: " + article.getTitle());
//            });

    }
}

