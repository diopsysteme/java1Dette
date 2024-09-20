package org.example.services;

import org.example.entities.Client;
import org.example.repositories.contract.ClientRepositoryI;
import org.example.services.contract.ClientServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceI {

    private final ClientRepositoryI clientRepository;

    @Autowired
    public ClientService(@Qualifier("clientRepositoryJdbc") ClientRepositoryI clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Collection<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> find(Long id) {
        return clientRepository.find(id);
    }

    @Override
    public void update(Client client) {
        clientRepository.update(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }
}
