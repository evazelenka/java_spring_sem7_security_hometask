package com.example.java_spring_sem7_security_hometask.service.impl;

import com.example.java_spring_sem7_security_hometask.domain.User;
import com.example.java_spring_sem7_security_hometask.repository.UserRepository;
import com.example.java_spring_sem7_security_hometask.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findByName(String username) {
        return repository.findByName(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteByName(String username) {
        repository.deleteByName(username);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
