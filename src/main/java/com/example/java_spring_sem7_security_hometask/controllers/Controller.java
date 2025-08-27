package com.example.java_spring_sem7_security_hometask.controllers;

import com.example.java_spring_sem7_security_hometask.domain.User;
import com.example.java_spring_sem7_security_hometask.repository.UserRepository;
import com.example.java_spring_sem7_security_hometask.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class Controller {

    private IUserService service;

    @GetMapping("/welcome")
    public String welcome(){
        return "This is unprotected page";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String pageForUser(){
        return "This is page only for users";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdmins(){
        return "This is page only for admins";
    }

    @GetMapping("/all")
    public String pageForAll(){
        return "This is page for all employees";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestBody User user){
//        User userFromDb = repository.findByName(user.getName()).orElse(null);
        User userFromDb = service.findByName(user.getName()).orElse(null);
        if(userFromDb != null){
            return "User has been already registered";
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        service.save(user);
        return "Registration done successfully";
    }

}
