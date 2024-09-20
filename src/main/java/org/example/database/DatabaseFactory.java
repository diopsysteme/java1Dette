package org.example.database;
import org.example.database.contract.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
@Component
public class DatabaseFactory  implements Database { private Connection connection;

    // Charger les configurations depuis le fichier YAML (ou ResourceBundle ici pour la simplicité)
    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;


    @Override
    public Connection getConnexion() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
        return connection;
    }

    @Override
    public void closeConnexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close to the database", e);
        }
    }
    @Override
    public ResultSet executeQuery(String query, Object... params) {

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = getConnexion().prepareStatement(query)) {
            if (params.length==0){
                preparedStatement.executeQuery();
            }else{
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query on the database", e);
        }
        return resultSet;
    }
    @Override
    public int executeUpdate(String query, Object... params) {
        int result = 0;
        try (PreparedStatement preparedStatement = getConnexion().prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute update on the database", e);
        }
        return result;
    }
}
