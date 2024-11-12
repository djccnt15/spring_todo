package com.demo.todo.domain.task.business;

import com.demo.todo.annotations.Business;
import com.demo.todo.domain.task.converter.TaskConverter;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.model.TaskResponse;
import com.demo.todo.domain.task.service.TaskService;
import com.demo.todo.enums.TaskStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Business
@RequiredArgsConstructor
public class TaskBusiness {

    private final TaskService service;
    private final TaskConverter converter;

    public TaskResponse create(TaskRequest request) {
        var entity = service.create(request);
        return converter.toResponse(entity);
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
}
