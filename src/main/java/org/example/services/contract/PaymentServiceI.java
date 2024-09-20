package org.example.services.contract;

import org.example.entities.Payment;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PaymentServiceI {
    void save(Payment article);

    Collection<Payment> findAll();

    Optional<Payment> find(Long id);

    void update(Payment article);

    void delete(Long id);

    List<Payment> findByDebtId(Long id);
}
