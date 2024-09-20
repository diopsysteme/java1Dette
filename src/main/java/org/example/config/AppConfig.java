package org.example.config;

import org.example.entities.Article;
import org.example.repositories.ArticleRepositoryCollection;
import org.example.repositories.contract.ArticleRepositoryI;
import org.example.JDBC.ArticleRepositoryJdbc;
import org.example.database.contract.Database;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@PropertySource("classpath:application.property")
@ComponentScan(basePackages = "org.example")
public class AppConfig {


    @Value("${repository.default}")
    private String defaultRepository;

    @Bean
    @Qualifier("collectionRepo")
    public ArticleRepositoryI articleRepositoryCollection(Collection<Article> collection) {
        return new ArticleRepositoryCollection(collection);
    }

    @Bean
    @Qualifier("jdbcRepo")
    public ArticleRepositoryI articleRepositoryJdbc(Database database) {
        return new ArticleRepositoryJdbc(database);
    }

    @Bean
    public ArticleRepositoryI articleRepository(Collection<Article> collection, Database database) {
        if ("collectionRepo".equalsIgnoreCase(defaultRepository)) {
            return articleRepositoryCollection(collection);
        } else if ("jdbcRepo".equalsIgnoreCase(defaultRepository)) {
            return articleRepositoryJdbc(database);
        } else {
            throw new IllegalArgumentException("Invalid repository type specified: " + defaultRepository);
        }
    }

    @Bean
    public <T>Collection<T> collection() {
        return new ArrayList<T>();
    }



}
