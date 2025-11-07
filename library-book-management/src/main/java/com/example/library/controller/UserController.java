package com.example.library.controller;

import com.example.library.entity.AppUser;
import com.example.library.entity.Role;
import com.example.library.service.UserService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public List<AppUser> all(){ return userService.all(); }

    public static record CreateUserRequest(@NotBlank String username,
                                           @NotBlank String password,
                                           Role role,
                                           String fullName,
                                           String email){}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser create(@RequestBody CreateUserRequest req){
        return userService.create(req.username(), req.password(),
                req.role() == null ? Role.STUDENT : req.role(),
                req.fullName(), req.email());
    }
}
