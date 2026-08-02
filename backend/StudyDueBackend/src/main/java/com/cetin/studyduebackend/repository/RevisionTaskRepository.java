package com.cetin.studyduebackend.repository;

import com.cetin.studyduebackend.entity.RevisionTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RevisionTaskRepository extends JpaRepository<RevisionTask, Long> {
    List<RevisionTask> findByDeadlineIdAndDeadlineUserSubject(Long deadlineId, String subject);
    Optional<RevisionTask> findByIdAndDeadlineUserSubject(Long id, String subject);
}
