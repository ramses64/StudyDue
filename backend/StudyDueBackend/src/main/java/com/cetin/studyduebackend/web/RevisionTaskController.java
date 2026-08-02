package com.cetin.studyduebackend.web;

import com.cetin.studyduebackend.dto.CreateRevisionTaskRequest;
import com.cetin.studyduebackend.dto.RevisionTaskResponse;
import com.cetin.studyduebackend.service.RevisionTaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/revision-tasks")
public class RevisionTaskController {
    private final RevisionTaskService revisionTaskService;

    @PostMapping("/create/{deadlineId}")
    public ResponseEntity<RevisionTaskResponse> createRevisionTasks(@PathVariable Long deadlineId, @Valid @RequestBody CreateRevisionTaskRequest createRevisionTaskRequest, @AuthenticationPrincipal Jwt jwt) {
        return new ResponseEntity<>(this.revisionTaskService.createRevisionTask(deadlineId, jwt.getSubject(), createRevisionTaskRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{deadlineId}")
    public ResponseEntity<List<RevisionTaskResponse>> getRevisionTasksForDeadline(@PathVariable Long deadlineId, @AuthenticationPrincipal Jwt jwt) {
        return new ResponseEntity<>(this.revisionTaskService.getTasksByDeadline(deadlineId, jwt.getSubject()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RevisionTaskResponse> markRevisionTaskAsCompleted(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        return new ResponseEntity<>(this.revisionTaskService.markAsCompleted(id, jwt.getSubject()), HttpStatus.OK);
    }
}
