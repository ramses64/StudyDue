package com.cetin.studyduebackend.service;

import com.cetin.studyduebackend.dto.CreateDeadlineRequest;
import com.cetin.studyduebackend.dto.DeadlineResponse;
import com.cetin.studyduebackend.dto.DeadlineUpdateRequest;
import com.cetin.studyduebackend.entity.Deadline;

import java.util.List;

public interface DeadlineService {
    List<DeadlineResponse> getDeadlines(String subject);
    DeadlineResponse createDeadline(String jwtSubject, CreateDeadlineRequest createDeadlineRequest);
    void deleteDeadlineById(Long id, String subject);
    DeadlineResponse updateDeadline(Long deadlineId, String subject, DeadlineUpdateRequest request);
}
