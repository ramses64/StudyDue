package com.cetin.studyduebackend.web;

import com.cetin.studyduebackend.entity.User;
import com.cetin.studyduebackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getUser(@AuthenticationPrincipal Jwt jwt) {
        return new ResponseEntity<>(this.userService.getUser(jwt.getSubject()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@AuthenticationPrincipal Jwt jwt) {
        this.userService.createUser(jwt.getSubject());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
