package org.example.repositories.contract;

import org.example.entities.Debt;
import org.example.entities.Payment;

import java.util.Collection;
import java.util.List;

public interface PaymentRepositoryI extends Repository<Payment, Long> {
    public List<Payment> findByDebtId(Long debtId);

}
