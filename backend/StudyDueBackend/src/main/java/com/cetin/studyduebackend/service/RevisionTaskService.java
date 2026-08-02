package com.cetin.studyduebackend.service;

import com.cetin.studyduebackend.dto.CreateRevisionTaskRequest;
import com.cetin.studyduebackend.dto.RevisionTaskResponse;

import java.util.List;

public interface RevisionTaskService {
    RevisionTaskResponse createRevisionTask(
            Long deadlineId,
            String subject,
            CreateRevisionTaskRequest request
    );

    List<RevisionTaskResponse> getTasksByDeadline(Long deadlineId, String subject);
    RevisionTaskResponse markAsCompleted(Long revisionTaskId, String subject);
}
