package com.hms.backend.controller;

import com.hms.backend.Security.JwtService;
import com.hms.backend.dto.LoginDTO;
import com.hms.backend.entity.User;
import com.hms.backend.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository repository;
    private final JwtService jwtService;

    public AuthController(
            UserRepository repository,
            JwtService jwtService) {

        this.repository = repository;
        this.jwtService = jwtService;
    }

    BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setPassword(
                encoder.encode(user.getPassword()));

        return repository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!encoder.matches(
                dto.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}
