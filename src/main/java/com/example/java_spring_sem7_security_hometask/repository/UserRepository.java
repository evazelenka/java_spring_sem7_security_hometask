package com.example.java_spring_sem7_security_hometask.repository;

import com.example.java_spring_sem7_security_hometask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);
    void deleteByName(String username);
}
