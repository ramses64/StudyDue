package com.cetin.studyduebackend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateDeadlineRequest(
        @NotBlank String title,
        @NotBlank String course,

        @NotNull
        @Future
        LocalDateTime dueDate,

        @NotBlank String type,
        @NotBlank String difficulty,

        @Size(max = 1000)
        String notes
) {}
