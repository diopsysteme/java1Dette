package org.example.repositories.contract;

import org.example.entities.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryI extends Repository<Article, Long> {
    public List<Article> findByTitle(String title);

}
