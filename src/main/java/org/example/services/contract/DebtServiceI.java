package org.example.services.contract;

import org.example.entities.Article;
import org.example.entities.Debt;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DebtServiceI {
    void save(Debt article);

    Collection<Debt> findAll();

    Optional<Debt> find(Long id);

    void update(Debt article);

    void delete(Long id);
    List<Debt> findByClientId(Long clientId);
}
