package com.cetin.studyduebackend.web;

import com.cetin.studyduebackend.dto.CreateDeadlineRequest;
import com.cetin.studyduebackend.dto.DeadlineResponse;
import com.cetin.studyduebackend.dto.DeadlineUpdateRequest;
import com.cetin.studyduebackend.service.DeadlineService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/deadlines")
public class DeadlineController {
    private final DeadlineService deadlineService;

    @GetMapping
    public ResponseEntity<List<DeadlineResponse>> getDeadlinesOfUser(@AuthenticationPrincipal Jwt jwt) {
        return new ResponseEntity<>(this.deadlineService.getDeadlines(jwt.getSubject()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DeadlineResponse> createDeadline(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateDeadlineRequest createDeadlineRequest) {
        DeadlineResponse deadlineResponse = this.deadlineService.createDeadline(jwt.getSubject(), createDeadlineRequest);
        return new ResponseEntity<>(deadlineResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeadline(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        this.deadlineService.deleteDeadlineById(id, jwt.getSubject());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{deadlineId}")
    public ResponseEntity<DeadlineResponse> updateDeadline(@PathVariable Long deadlineId, @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody DeadlineUpdateRequest deadlineUpdateRequest) {
        return new ResponseEntity<>(this.deadlineService.updateDeadline(deadlineId, jwt.getSubject(), deadlineUpdateRequest), HttpStatus.OK);
    }
}
