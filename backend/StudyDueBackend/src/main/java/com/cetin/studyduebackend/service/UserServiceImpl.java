package com.cetin.studyduebackend.service;

import com.cetin.studyduebackend.entity.User;
import com.cetin.studyduebackend.exception.UserNotFoundException;
import com.cetin.studyduebackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User getUser(String jwtSubject) {
        return this.userRepository.findUserBySubject(jwtSubject).orElseThrow(() -> new UserNotFoundException(jwtSubject));
    }

    public void createUser(String jwtSubject) {
        if (this.userRepository.findUserBySubject(jwtSubject).isEmpty()) {
            User newUser = new User();
            newUser.setSubject(jwtSubject);
            this.userRepository.save(newUser);
        }
    }
}
