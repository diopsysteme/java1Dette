package org.example.repositories.contract;

import org.example.entities.Debt;

import java.util.List;

public interface DebtRepositoryI extends Repository<Debt, Long> {
    public List<Debt> findByClientId(Long clientId);
}
