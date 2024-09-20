package org.example.repositories;

import org.example.entities.Article;
import org.example.repositories.contract.ArticleRepositoryI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
@Qualifier("articleRepositoryCollection")
public class ArticleRepositoryCollection extends CollectionRepository<Article,Long> implements ArticleRepositoryI {
    public ArticleRepositoryCollection(Collection<Article> collection) {
        super(collection);

    }
    @Override
    public List<Article> findByTitle(String title) {
        return collection.stream()
                .filter(article -> article.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
}
