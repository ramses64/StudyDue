package com.cetin.studyduebackend.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateRevisionTaskRequest(
        @NotBlank String title
) {

}