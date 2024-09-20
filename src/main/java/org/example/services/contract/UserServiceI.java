package org.example.services.contract;

import org.example.entities.Article;
import org.example.entities.User;

import java.util.Collection;
import java.util.Optional;

public interface UserServiceI {
    void save(User article);

    Collection<User> findAll();

    Optional<User> find(Long id);

    void update(User article);

    void delete(Long id);
}
