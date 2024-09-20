package org.example.JDBC;

import org.example.database.DatabaseFactory;
import org.example.database.contract.Database;
import org.example.entities.Debt;
import org.example.repositories.contract.DebtRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("debtRepositoryJdbc")
public class DebtRepositoryJdbc extends JdbcRepository<Debt, Long> implements DebtRepositoryI {

    @Autowired
    public DebtRepositoryJdbc(Database databaseFactory) {
        super(databaseFactory, Debt.class);
        this.table = "debts";
    }

    public List<Debt> findByClientId(Long clientId) {
        String sql = "SELECT * FROM " + table + " WHERE clientId = ?";
        List<Debt> debts = new ArrayList<>();
        try (ResultSet rs = databaseFactory.executeQuery(sql, clientId)) {
            while (rs.next()) {
                Debt debt = createEntityFromResultSet(rs);
                debts.add(debt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debts;
    }
}
