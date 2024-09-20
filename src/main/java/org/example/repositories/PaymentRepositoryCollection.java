package org.example.repositories;

import org.example.entities.Payment;
import org.example.repositories.contract.PaymentRepositoryI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("paymentRepositoryCollection")
public class PaymentRepositoryCollection extends CollectionRepository<Payment, Long> implements PaymentRepositoryI {

    public PaymentRepositoryCollection(Collection<Payment> collection) {
        super(collection);
    }

    @Override
    public List<Payment> findByDebtId(Long debtId) {
        return collection.stream()
                .filter(payment -> payment.getDebtId() == debtId)
                .collect(Collectors.toList());
    }



}
