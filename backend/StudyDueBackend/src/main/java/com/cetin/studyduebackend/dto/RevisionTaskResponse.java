package com.cetin.studyduebackend.dto;

public record RevisionTaskResponse(
        Long id,
        String title,
        boolean completed,
        Long deadlineId
) {
}