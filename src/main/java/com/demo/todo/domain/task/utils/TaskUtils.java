package com.demo.todo.domain.task.utils;

import com.demo.todo.db.TaskRepository;
import com.demo.todo.db.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskUtils {
    
    private final TaskRepository taskRepository;
    
    public TaskEntity getById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(
                () -> new IllegalArgumentException(String.format("not exist task id: %d", id))
            );
    }
}
