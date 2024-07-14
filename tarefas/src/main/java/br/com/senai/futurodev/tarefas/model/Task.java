package br.com.senai.futurodev.tarefas.model;

import br.com.senai.futurodev.tarefas.model.enums.TaskPriorityEnum;
import br.com.senai.futurodev.tarefas.model.enums.TaskStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class Task {

    @NotNull
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private TaskStatusEnum status;

    @NotNull(message = "O campo prioridade deve ser devidamente preenchido")
    private TaskPriorityEnum priority;

    @NotNull
    @Valid
    private Assignee assignee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    public TaskPriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(TaskPriorityEnum priority) {
        this.priority = priority;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }
}
