package com.demo.todo.domain.task.converter;

import com.demo.todo.annotations.Converter;
import com.demo.todo.db.entity.TaskEntity;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.model.TaskResponse;
import com.demo.todo.enums.TaskStatus;

@Converter
public class TaskConverter {

    public TaskResponse toResponse(TaskEntity task) {
        return TaskResponse.builder()
            .id(task.getId())
            .title(task.getTitle())
            .description(task.getDescription())
            .status(task.getStatus())
            .dueDate(task.getDueDate())
            .createdAt(task.getCreatedAt().toLocalDateTime())
            .updatedAt(task.getUpdatedAt().toLocalDateTime())
            .build();
    }
    
    public TaskEntity toEntity(TaskRequest task) {
        return TaskEntity.builder()
            .title(task.getTitle())
            .description(task.getDescription())
            .dueDate(task.getDueDate())
            .status(TaskStatus.TODO)
            .build();
    }
}
