package com.cetin.studyduebackend.service;

import com.cetin.studyduebackend.entity.User;

public interface UserService {
    User getUser(String jwtSubject);
    void createUser(String jwtSubject);
}
