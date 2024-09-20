package org.example.JDBC;

import org.example.database.DatabaseFactory;
import org.example.database.contract.Database;
import org.example.repositories.contract.Repository;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
public abstract class JdbcRepository<T , ID> implements Repository<T, ID> {

    protected String table;
    public final Database databaseFactory;
    private final Class<T> entityClass;

    public JdbcRepository(Database databaseFactory, Class<T> entityClass) {
        this.databaseFactory = databaseFactory;
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + table + " (");
        StringBuilder values = new StringBuilder("VALUES (");

        Field[] fields = entityClass.getDeclaredFields();
        String idColumn = "id";

        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equals(idColumn)) {
                sql.append(field.getName()).append(", ");
                values.append("?, ");
            }
        }

        sql.setLength(sql.length() - 2);
        values.setLength(values.length() - 2);
        sql.append(") ").append(values).append(")");

        try {
            Object[] fieldValues = getFieldValuesExcludingId(entity, fields, idColumn);
            databaseFactory.executeUpdate(sql.toString(), fieldValues);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private Object[] getFieldValuesExcludingId(T entity, Field[] fields, String idColumn) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equals(idColumn)) {
                values.add(field.get(entity));
            }
        }
        return values.toArray();
    }

    private Object[] getFieldValuesExcluding(T entity, Field[] fields, String[] excludeFields) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        Set<String> excludeFieldSet = new HashSet<>(Arrays.asList(excludeFields));

        for (Field field : fields) {
            field.setAccessible(true);
            if (!excludeFieldSet.contains(field.getName())) {
                values.add(field.get(entity));
            }
        }
        return values.toArray();
    }
//    @Override
//    public void save(T entity) {
//        StringBuilder sql = new StringBuilder("INSERT INTO " + table + " (");
//        StringBuilder values = new StringBuilder("VALUES (");
//
//        Field[] fields = entity.getClass().getDeclaredFields();
//        String idColumn = "id"; // Assuming the ID column is named "id", adjust if needed
//
//        // Build columns and values, excluding the ID field
//        for (Field field : fields) {
//            field.setAccessible(true);
//            if (!field.getName().equals(idColumn)) {
//                sql.append(field.getName()).append(", ");
//                values.append("?, ");
//            }
//        }
//
//        sql.setLength(sql.length() - 2); // Remove the last comma
//        values.setLength(values.length() - 2); // Remove the last comma
//        sql.append(") ").append(values).append(")");
//
//        try {
//            Object[] fieldValues = getFieldValuesExcludingId(entity, fields, idColumn);
//            databaseFactory.executeUpdate(sql.toString(), fieldValues);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public List<T> findAll() {
        List<T> resultList = new ArrayList<>();
        String sql = "SELECT * FROM " + table;

        try (Connection conn = databaseFactory.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                T entity = createEntityFromResultSet(rs);
                resultList.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all records", e); // Or use DatabaseOperationException
        }
        return resultList;
    }



    @Override
    public void update(T entity) {
        StringBuilder sql = new StringBuilder("UPDATE " + table + " SET ");

        Field[] fields = entityClass.getDeclaredFields();
        Object idValue = null;
        String idColumn = "id";

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName().equals(idColumn)) {
                    idValue = field.get(entity);
                } else {
                    sql.append(field.getName()).append(" = ?, ");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sql.setLength(sql.length() - 2); // Remove the last comma
        sql.append(" WHERE ").append(idColumn).append(" = ?");

        try {
            Object[] params = getFieldValues(entity, fields);
            databaseFactory.executeUpdate(sql.toString(), appendId(params, idValue));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(ID id) {
        String sql = "DELETE FROM " + table + " WHERE id = ?";
        databaseFactory.executeUpdate(sql, id);
    }

    @Override
    public Optional<T> find(ID id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?";
        T entity = null;

        try (ResultSet rs = databaseFactory.executeQuery(sql, id)) {
            if (rs.next()) {
                entity = createEntityFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(entity);
    }

    // Method to create an entity from ResultSet
    public T createEntityFromResultSet(ResultSet rs) {
        T entity = null;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
            Field[] fields = entityClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = rs.getObject(field.getName());  // Can use a custom mapping mechanism
                field.set(entity, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }


    // Helper method to get field values from an entity
    private Object[] getFieldValues(T entity, Field[] fields) throws IllegalAccessException {
        Object[] values = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            values[i] = fields[i].get(entity);
        }
        return values;
    }

    // Helper method to append ID to the parameters
    private Object[] appendId(Object[] params, Object id) {
        Object[] newParams = new Object[params.length + 1];
        System.arraycopy(params, 0, newParams, 0, params.length);
        newParams[params.length] = id;
        return newParams;
    }
}
