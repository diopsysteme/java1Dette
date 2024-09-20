package org.example.services.contract;

import org.example.entities.Article;
import org.example.entities.Client;

import java.util.Collection;
import java.util.Optional;

public interface ClientServiceI {
    void save(Client article);

    Collection<Client> findAll();

    Optional<Client> find(Long id);

    void update(Client article);

    void delete(Long id);
}
