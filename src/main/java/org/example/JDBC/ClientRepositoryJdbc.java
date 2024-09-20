package org.example.JDBC;

import org.example.database.contract.Database;
import org.example.entities.Client;
import org.example.entities.Debt;
import org.example.repositories.contract.ClientRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("clientRepositoryJdbc")
public class ClientRepositoryJdbc extends JdbcRepository<Client, Long> implements ClientRepositoryI {

    @Autowired
    public ClientRepositoryJdbc(Database databaseFactory) {
        super(databaseFactory, Client.class);
        this.table = "clients";
    }



    @Override
public List<Client> findBySurname(String surname) {
        String sql = "SELECT * FROM " + table + " WHERE surname = ?";
        List<Client> clients = new ArrayList<>();
        try (ResultSet rs = databaseFactory.executeQuery(sql, surname)) {
            while (rs.next()) {
                Client client = createEntityFromResultSet(rs);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
