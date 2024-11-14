package com.demo.todo.domain.task.service;

import com.demo.todo.db.TaskRepository;
import com.demo.todo.db.entity.TaskEntity;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.model.TaskStatusRequest;
import com.demo.todo.domain.task.utils.TaskUtils;
import com.demo.todo.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    
    public TaskEntity updateTask(Long id, TaskRequest task) {
        var taskEntity = taskUtils.getById(id);
        
        taskEntity.setTitle(
            Strings.isEmpty(task.getTitle()) ? taskEntity.getTitle() : task.getTitle()
        );
        taskEntity.setDescription(
            Strings.isEmpty(task.getDescription()) ? taskEntity.getDescription() : task.getDescription()
        );
        taskEntity.setDueDate(
            Objects.isNull(task.getDueDate()) ? taskEntity.getDueDate() : task.getDueDate()
        );
        
        return taskRepository.save(taskEntity);
    }
    
    public TaskEntity updateTaskStatus(Long id, TaskStatusRequest statusRequest) {
        var taskEntity = taskUtils.getById(id);
        taskEntity.setStatus(statusRequest.getStatus());
        return taskRepository.save(taskEntity);
    }
    
    public boolean deleteTask(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            log.error("an error raised for deleting task: {}", e.toString());
            return false;
        }
        return true;
    }
}
