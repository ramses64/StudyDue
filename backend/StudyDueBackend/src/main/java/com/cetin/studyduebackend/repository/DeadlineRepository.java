package com.cetin.studyduebackend.repository;

import com.cetin.studyduebackend.entity.Deadline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DeadlineRepository extends JpaRepository<Deadline, Long> {

    Optional<Deadline> findByIdAndUser_Subject(Long id, String subject);
    boolean existsByIdAndUser_Subject(Long id, String subject);
    List<Deadline> findAllByUserSubject(String subject);
}
