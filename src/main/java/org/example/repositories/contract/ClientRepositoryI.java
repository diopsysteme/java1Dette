package org.example.repositories.contract;

import org.example.entities.Client;
import org.example.entities.Debt;

import java.util.List;

public interface ClientRepositoryI extends Repository<Client, Long> {

    List<Client> findBySurname(String surname);
}
