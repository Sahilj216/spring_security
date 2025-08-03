package com.ttn.controller;

import com.ttn.dto.UserRegisterDto;
import com.ttn.entities.Role;
import com.ttn.entities.UserEntity;
import com.ttn.repo.RoleRepository;
import com.ttn.repo.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserRegisterDto userRegisterDto) {

        if (userEntityRepository.findByEmail(userRegisterDto.email()).isPresent()) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        userEntityRepository.save(
                new UserEntity(
                        userRegisterDto.email(),
                        userRegisterDto.name(),
                        passwordEncoder.encode(userRegisterDto.password()),
                        Set.of(role)
                )
        );

        return ResponseEntity.ok("User registered successfully");
    }
}
