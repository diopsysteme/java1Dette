package org.example.services;

import org.example.entities.Article;
import org.example.repositories.contract.ArticleRepositoryI;
import org.example.services.contract.ArticleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class ArticleService implements ArticleServiceI {

    private final ArticleRepositoryI articleRepository;


    @Autowired
    public ArticleService(@Qualifier("articleRepositoryCollection") ArticleRepositoryI articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Collection<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> find(Long id) {
        return articleRepository.find(id);
    }

    @Override
    public void update(Article article) {
        articleRepository.update(article);
    }

    @Override
    public void delete(Long id) {
        articleRepository.delete(id);
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }
}
