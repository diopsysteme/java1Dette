package org.example.services.contract;

import org.example.entities.Article;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ArticleServiceI {
    void save(Article article);

    Collection<Article> findAll();

    Optional<Article> find(Long id);

    void update(Article article);

    void delete(Long id);
}
