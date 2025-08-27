package com.example.java_spring_sem7_security_hometask.config;

import com.example.java_spring_sem7_security_hometask.domain.User;
import com.example.java_spring_sem7_security_hometask.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user2 = repository.findByName(username);
////        User user = new User();
////        user.setName("user");
////        user.setPassword("user");
////        user.setRole("ROLE_USER");
//
////        repository.save(user);
////        repository.save(admin);
//        repository.deleteById(1L);
//        repository.deleteById(2L);
//        return  user2.map(MyUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException(username+" There is not such user in repository"));

        return repository.findByName(username)
                .map(u -> org.springframework.security.core.userdetails.User
                        .withUsername(u.getName())
                        .password(u.getPassword())
                        .authorities("ROLE_ADMIN")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
