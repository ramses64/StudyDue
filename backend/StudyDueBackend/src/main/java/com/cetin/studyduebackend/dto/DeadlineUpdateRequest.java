package com.cetin.studyduebackend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeadlineUpdateRequest {

    @NotBlank
    String title;
    @NotBlank String course;

    @NotNull
    @Future
    LocalDateTime dueDate;

    @NotBlank String type;
    @NotBlank String difficulty;

    @Size(max = 1000)
    String notes;
}