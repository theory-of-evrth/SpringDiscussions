package com.keschubay.discussions.controller;

import com.keschubay.discussions.model.AppUser;
import com.keschubay.discussions.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService userService;
    private final PasswordEncoder passwordEncoder;

    public AppUserController(AppUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    public ResponseEntity<AppUser> createUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role
    ) {
        AppUser createdUser = userService.createUser(new AppUser(username, passwordEncoder.encode(password), role));
        return ResponseEntity.ok(createdUser);
    }
}