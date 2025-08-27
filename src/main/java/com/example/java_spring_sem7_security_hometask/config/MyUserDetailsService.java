package com.example.java_spring_sem7_security_hometask.config;

import com.example.java_spring_sem7_security_hometask.domain.User;
import com.example.java_spring_sem7_security_hometask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user2 = repository.findByName(username);
        return  user2.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username+" There is not such user in repository"));
    }
}
