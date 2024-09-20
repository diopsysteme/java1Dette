package org.example.services;

import org.example.entities.User;
import org.example.repositories.contract.UserRepositoryI;
import org.example.services.contract.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements UserServiceI {

    private final UserRepositoryI userRepository;

    @Autowired
    public UserService(@Qualifier("userRepository"+"Jdbc")UserRepositoryI userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> find(Long id) {
        return userRepository.find(id);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
