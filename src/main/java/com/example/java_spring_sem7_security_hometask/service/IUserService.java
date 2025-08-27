package com.example.java_spring_sem7_security_hometask.service;

import com.example.java_spring_sem7_security_hometask.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {
    User save(User user);
    Optional<User> findByName(String username);
    Optional<User> findById(Long id);
    void deleteByName(String username);
    void deleteById(Long id);
}
