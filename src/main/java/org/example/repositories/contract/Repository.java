package org.example.repositories.contract;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    void save(T entity);

    Collection<T> findAll();

    void update(T entity);

    void delete(ID id);

    Optional<T> find(ID id);
}
