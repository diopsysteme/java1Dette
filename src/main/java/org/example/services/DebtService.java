package org.example.services;

import org.example.entities.Debt;
import org.example.repositories.contract.DebtRepositoryI;
import org.example.services.contract.DebtServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DebtService implements DebtServiceI {

    private final DebtRepositoryI debtRepository;

    @Autowired
    public DebtService(@Qualifier("debtRepositoryJdbc") DebtRepositoryI debtRepository) {
        this.debtRepository = debtRepository;
    }

    @Override
    public void save(Debt debt) {
        debtRepository.save(debt);
    }

    @Override
    public Collection<Debt> findAll() {
        return debtRepository.findAll();
    }

    @Override
    public Optional<Debt> find(Long id) {
        return debtRepository.find(id);
    }

    @Override
    public void update(Debt debt) {
        debtRepository.update(debt);
    }

    @Override
    public void delete(Long id) {
        debtRepository.delete(id);
    }
    public List<Debt> findByClientId(Long userId) {
        return debtRepository.findByClientId(userId);
    }
}