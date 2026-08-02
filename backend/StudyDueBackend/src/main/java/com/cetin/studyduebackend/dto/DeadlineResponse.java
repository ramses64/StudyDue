package com.cetin.studyduebackend.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DeadlineResponse(
        Long id,
        String title,
        String course,
        LocalDateTime dueDate,
        String type,
        String difficulty,
        String notes,
        List<RevisionTaskResponse> tasks
) {
}