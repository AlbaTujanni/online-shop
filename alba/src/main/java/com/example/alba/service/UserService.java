package com.example.alba.service;

import com.example.alba.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService  extends UserDetailsService {
    User findByName (String name);
    User save(User registration);
}
