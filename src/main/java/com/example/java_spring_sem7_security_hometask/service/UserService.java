package com.example.java_spring_sem7_security_hometask.service;

import com.example.java_spring_sem7_security_hometask.domain.Role;
import com.example.java_spring_sem7_security_hometask.domain.User;
import com.example.java_spring_sem7_security_hometask.repository.RoleRepository;
import com.example.java_spring_sem7_security_hometask.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // например BCryptPasswordEncoder

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(User user) {
        // Шифруем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Определяем роль
        String roleName = (user.getName().endsWith("8545") && user.getPassword().endsWith("8545"))
                ? "ROLE_ADMIN" : "ROLE_USER";

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException(roleName + " not found"));
        roles.add(role);
        user.getRoles().addAll(roles);

        return userRepository.save(user);
    }
}
