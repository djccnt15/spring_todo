package com.demo.todo.domain.task.converter;

import com.demo.todo.annotations.Converter;
import com.demo.todo.db.entity.TaskEntity;
import com.demo.todo.domain.task.model.TaskResponse;

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
}
