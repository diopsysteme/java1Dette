package org.example.JDBC;

import org.example.database.DatabaseFactory;
import org.example.database.contract.Database;
import org.example.entities.Payment;
import org.example.repositories.contract.PaymentRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("paymentRepositoryJdbc")
public class PaymentRepositoryJdbc extends JdbcRepository<Payment, Long> implements PaymentRepositoryI {

    @Autowired
    public PaymentRepositoryJdbc(Database databaseFactory) {
        super(databaseFactory, Payment.class);
        this.table = "payments";
    }

    public List<Payment> findByDebtId(Long debtId) {
        String sql = "SELECT * FROM " + table + " WHERE debt_id = ?";
        List<Payment> payments = new ArrayList<>();
        try (ResultSet rs = databaseFactory.executeQuery(sql, debtId)) {
            while (rs.next()) {
                Payment payment = createEntityFromResultSet(rs);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
