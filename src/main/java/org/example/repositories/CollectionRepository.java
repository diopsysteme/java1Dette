package org.example.repositories;
import java.lang.reflect.Field;

import org.example.entities.Payment;
import org.example.repositories.contract.Repository;

import java.util.*;

public abstract class CollectionRepository<T, ID> implements Repository<T, ID> {

    protected  Collection<T> collection;

    public CollectionRepository(Collection<T> d) {
        this.collection = d;
    }

@Override
    public void save(T entity) {
        try {
            Field[] fields = entity.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);

                System.out.println("Field: " + field.getName() + " | Value: " + value);
            }
            collection.add(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Collection<T> findAll() {
        return collection;
    }

    @Override
    public void update(T entity) {
        try {
            Optional<T> existingEntityOpt = find(getId(entity));

            if (existingEntityOpt.isPresent()) {
                T existingEntity = existingEntityOpt.get();

                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object newValue = field.get(entity);
                    field.set(existingEntity, newValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(ID id) {
        // Trouver l'entité par ID
        Optional<T> entityToDelete = find(id);

        // Si l'entité est trouvée, on la supprime
        entityToDelete.ifPresent(collection::remove);
    }


    @Override
    public Optional<T> find(ID id) {
        return collection.stream()
                .filter(entity -> {
                    try {
                        // Obtenir l'ID de chaque entité
                        ID entityId = getId(entity);  // `getId` est à implémenter pour récupérer l'ID via réflexion
                        return entityId.equals(id);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .findFirst();
    }

    private ID getId(T entity) throws IllegalAccessException {
        Field idField;
        try {
            idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return (ID) idField.get(entity);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("ID field not found", e);
        }
    }


}
