package com.cetin.studyduebackend.service;

import com.cetin.studyduebackend.dto.CreateDeadlineRequest;
import com.cetin.studyduebackend.dto.DeadlineResponse;
import com.cetin.studyduebackend.dto.DeadlineUpdateRequest;
import com.cetin.studyduebackend.dto.RevisionTaskResponse;
import com.cetin.studyduebackend.entity.Deadline;
import com.cetin.studyduebackend.entity.User;
import com.cetin.studyduebackend.exception.DeadlineNotFoundException;
import com.cetin.studyduebackend.exception.UserNotFoundException;
import com.cetin.studyduebackend.repository.DeadlineRepository;
import com.cetin.studyduebackend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@AllArgsConstructor
@Service
public class DeadlineServiceImpl implements DeadlineService {
    private final DeadlineRepository deadlineRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DeadlineResponse> getDeadlines(String subject) {
        System.out.println(
                "Transaction active: " +
                        TransactionSynchronizationManager.isActualTransactionActive()
        );
        return this.deadlineRepository.findAllByUserSubject(subject).stream().map(this::toResponse).toList();
    }

    @Transactional
    public DeadlineResponse createDeadline(String jwtSubject, CreateDeadlineRequest createDeadlineRequest) {
        User user = this.userRepository.findUserBySubject(jwtSubject).orElseThrow(() -> new UserNotFoundException(jwtSubject));

        Deadline deadline = new Deadline();
        deadline.setTitle(createDeadlineRequest.title());
        deadline.setCourse(createDeadlineRequest.course());
        deadline.setDueDate(createDeadlineRequest.dueDate());
        deadline.setType(createDeadlineRequest.type());
        deadline.setDifficulty(createDeadlineRequest.difficulty());
        deadline.setNotes(createDeadlineRequest.notes());
        deadline.setUser(user);

        Deadline savedDeadline = deadlineRepository.save(deadline);

        return toResponse(savedDeadline);
    }

    private DeadlineResponse toResponse(Deadline deadline) {
        return new DeadlineResponse(
                deadline.getId(),
                deadline.getTitle(),
                deadline.getCourse(),
                deadline.getDueDate(),
                deadline.getType(),
                deadline.getDifficulty(),
                deadline.getNotes(),
                deadline.getRevisionTasks()
                        .stream()
                        .map(task -> new RevisionTaskResponse(
                                task.getId(),
                                task.getTitle(),
                                task.isCompleted(),
                                task.getDeadline().getId()
                        ))
                        .toList()
        );
    }

    @Transactional
    public void deleteDeadlineById(Long id, String subject) {
        if (!deadlineRepository.existsByIdAndUser_Subject(id, subject)) throw new DeadlineNotFoundException(id);

        deadlineRepository.deleteById(id);
    }

    @Transactional
    public DeadlineResponse updateDeadline(Long deadlineId, String subject, DeadlineUpdateRequest request) {
        Deadline deadline = this.deadlineRepository.findByIdAndUser_Subject(deadlineId, subject).orElseThrow(() -> new DeadlineNotFoundException(deadlineId));

        deadline.setTitle(request.getTitle());
        deadline.setCourse(request.getCourse());
        deadline.setDueDate(request.getDueDate());
        deadline.setType(request.getType());
        deadline.setDifficulty(request.getDifficulty());
        deadline.setNotes(request.getNotes());

        return toResponse(this.deadlineRepository.save(deadline));
    }
}
