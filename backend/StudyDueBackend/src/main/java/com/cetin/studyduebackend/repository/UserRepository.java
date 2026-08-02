package com.cetin.studyduebackend.repository;

import com.cetin.studyduebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserBySubject(String subject);
}
