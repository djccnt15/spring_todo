package com.demo.todo.domain.task.service;

import com.demo.todo.db.TaskRepository;
import com.demo.todo.db.entity.TaskEntity;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.utils.TaskUtils;
import com.demo.todo.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final TaskUtils taskUtils;

    public TaskEntity create(TaskRequest request) {
        var taskEntity = TaskEntity.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .dueDate(request.getDueDate())
            .status(TaskStatus.TODO)
            .build();
        return taskRepository.save(taskEntity);
    }
    
    public List<TaskEntity> getAllTask() {
        return taskRepository.findAll();
    }
    
    public TaskEntity getById(Long id) {
        return taskUtils.getById(id);
    }
    
    public List<TaskEntity> getByDueDate(LocalDate dueDate) {
        return taskRepository.findByDueDate(dueDate);
    }
    
    public List<TaskEntity> getByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}
