package org.example.database.contract;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Database {
    Connection getConnexion();
    void closeConnexion();
    ResultSet executeQuery(String query, Object... params);
    int executeUpdate(String query, Object... params);
}
