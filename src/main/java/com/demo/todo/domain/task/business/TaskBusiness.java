package com.demo.todo.domain.task.business;

import com.demo.todo.annotations.Business;
import com.demo.todo.domain.task.converter.TaskConverter;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.model.TaskResponse;
import com.demo.todo.domain.task.model.TaskStatusRequest;
import com.demo.todo.domain.task.service.TaskService;
import com.demo.todo.enums.TaskStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Business
@RequiredArgsConstructor
public class TaskBusiness {

    private final TaskService service;
    private final TaskConverter converter;

    public TaskResponse create(TaskRequest request) {
        var entity = converter.toEntity(request);
        var taskEntity = service.create(entity);
        return converter.toResponse(taskEntity);
    }
    
    public TaskResponse getById(Long id) {
        var taskEntity = service.getById(id);
        return converter.toResponse(taskEntity);
    }
    
    public List<TaskResponse> getAll() {
        return service.getAllTask().stream()
            .map(converter::toResponse)
            .toList();
    }
    
    public List<TaskResponse> getByDueDate(LocalDate dueDate) {
        return service.getByDueDate(dueDate).stream()
            .map(converter::toResponse)
            .toList();
    }
    
    public List<TaskResponse> getByStatus(TaskStatus status) {
        return service.getByStatus(status).stream()
            .map(converter::toResponse)
            .toList();
    }
    
    public List<TaskStatus> getAllStatus() {
        var statusList = TaskStatus.values();
        return Arrays.stream(statusList).toList();
    }
    
    public TaskResponse updateTask(Long id, TaskRequest task) {
        var taskEntity = service.updateTask(id, task);
        return converter.toResponse(taskEntity);
    }
    
    public TaskResponse updateTaskStatus(Long id, TaskStatusRequest statusRequest) {
        var taskEntity = service.updateTaskStatus(id, statusRequest);
        return converter.toResponse(taskEntity);
    }
    
    public boolean deleteTask(Long id) {
        return service.deleteTask(id);
    }
}
