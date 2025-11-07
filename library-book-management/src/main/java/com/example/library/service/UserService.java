package com.example.library.service;

import com.example.library.entity.AppUser;
import com.example.library.entity.Role;
import com.example.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public AppUser create(String username, String rawPassword, Role role, String fullName, String email) {
        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPasswordHash(encoder.encode(rawPassword));
        u.setRole(role);
        u.setFullName(fullName);
        u.setEmail(email);
        return repo.save(u);
    }

    public List<AppUser> all(){ return repo.findAll(); }

    public AppUser findByUsername(String username){
        return repo.findByUsername(username).orElseThrow();
    }
}
