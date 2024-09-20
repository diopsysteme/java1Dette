package org.example.services;

import org.example.entities.Payment;
import org.example.repositories.contract.PaymentRepositoryI;
import org.example.services.contract.PaymentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class PaymentService implements PaymentServiceI {

    private final PaymentRepositoryI paymentRepository;

    @Autowired
    public PaymentService(@Qualifier("paymentRepositoryCollection") PaymentRepositoryI paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Collection<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> find(Long id) {
        return paymentRepository.find(id);
    }

    @Override
    public void update(Payment payment) {
        paymentRepository.update(payment);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.delete(id);
    }
    @Override
    public List<Payment> findByDebtId(Long id){
        return paymentRepository.findByDebtId(id);
    }
}