package com.example.java_spring_sem7_security_hometask.service;

import com.example.java_spring_sem7_security_hometask.domain.User;
import com.example.java_spring_sem7_security_hometask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(String username, String rawPassword){

//        User admin = new User();
//        admin.setName("eva");
//        admin.setPassword(passwordEncoder.encode("eva"));
//        admin.setRole("ROLE_ADMIN");
//        repository.save(admin);

        User u = new User();
        u.setName(username);
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setRole("ROLE_USER");
        return repository.save(u);
    }

    public Optional<User> findByName(String username){
        return repository.findByName(username);
    }
}
