package com.cetin.studyduebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deadline")
public class Deadline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String course;
    private LocalDateTime dueDate;
    private String type;
    private String difficulty;

    @Column(length = 1000)
    private String notes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "deadline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RevisionTask> revisionTasks = new ArrayList<>();

    public void addRevisionTask(RevisionTask revisionTask) {
        this.revisionTasks.add(revisionTask);
        revisionTask.setDeadline(this);
    }

    public void removeRevisionTask(RevisionTask revisionTask) {
        this.revisionTasks.remove(revisionTask);
        revisionTask.setDeadline(null);
    }
}
