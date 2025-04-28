package com.aditumcr.taskmanager.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
