package com.demo.todo.domain.task.model;

import com.demo.todo.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    
    private Long id;
    
    private String title;
    
    private String description;
    
    private TaskStatus status;
    
    private LocalDate dueDate;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
