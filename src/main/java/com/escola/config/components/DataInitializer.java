package com.escola.config.components;

import com.escola.entidades.Role;
import com.escola.entidades.User;
import com.escola.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.text.ParseException;

@Component
public class DataInitializer {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws ParseException {
        addAdminUser();

    }

    private void addAdminUser() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}