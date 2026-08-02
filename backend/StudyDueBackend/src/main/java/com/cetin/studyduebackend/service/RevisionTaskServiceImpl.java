package com.cetin.studyduebackend.service;

import com.cetin.studyduebackend.dto.CreateRevisionTaskRequest;
import com.cetin.studyduebackend.dto.RevisionTaskResponse;
import com.cetin.studyduebackend.entity.Deadline;
import com.cetin.studyduebackend.entity.RevisionTask;
import com.cetin.studyduebackend.exception.DeadlineNotFoundException;
import com.cetin.studyduebackend.exception.RevisionTaskNotFoundException;
import com.cetin.studyduebackend.repository.DeadlineRepository;
import com.cetin.studyduebackend.repository.RevisionTaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RevisionTaskServiceImpl implements RevisionTaskService {
    private final RevisionTaskRepository revisionTaskRepository;
    private final DeadlineRepository deadlineRepository;

    @Transactional
    public RevisionTaskResponse createRevisionTask(
            Long deadlineId,
            String subject,
            CreateRevisionTaskRequest request
    ) {
        Deadline deadline = deadlineRepository.findByIdAndUser_Subject(deadlineId, subject)
                .orElseThrow(() -> new DeadlineNotFoundException(deadlineId));

        RevisionTask task = new RevisionTask();
        task.setTitle(request.title());
        task.setCompleted(false);
        deadline.addRevisionTask(task);

        RevisionTask savedTask = revisionTaskRepository.save(task);

        return mapToResponse(savedTask);
    }

    public List<RevisionTaskResponse> getTasksByDeadline(Long deadlineId, String subject) {
        return revisionTaskRepository.findByDeadlineIdAndDeadlineUserSubject(deadlineId, subject)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public RevisionTaskResponse markAsCompleted(Long revisionTaskId, String subject) {
        RevisionTask revisionTask = this.revisionTaskRepository.findByIdAndDeadlineUserSubject(revisionTaskId, subject).orElseThrow(() -> new RevisionTaskNotFoundException(revisionTaskId));
        revisionTask.setCompleted(!revisionTask.isCompleted());
        return this.mapToResponse(this.revisionTaskRepository.save(revisionTask));
    }

    private RevisionTaskResponse mapToResponse(RevisionTask task) {
        return new RevisionTaskResponse(
                task.getId(),
                task.getTitle(),
                task.isCompleted(),
                task.getDeadline().getId()
        );
    }
}
