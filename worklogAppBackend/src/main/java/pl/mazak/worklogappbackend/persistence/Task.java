package pl.mazak.worklogappbackend.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 10)
    @NotNull
    @Column(name = "jira_code", nullable = false, length = 10)
    private String jiraCode;

    @NotNull
    @Column(name = "time_worked", nullable = false)
    private Instant timeWorked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJiraCode() {
        return jiraCode;
    }

    public void setJiraCode(String jiraCode) {
        this.jiraCode = jiraCode;
    }

    public Instant getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(Instant timeWorked) {
        this.timeWorked = timeWorked;
    }

}