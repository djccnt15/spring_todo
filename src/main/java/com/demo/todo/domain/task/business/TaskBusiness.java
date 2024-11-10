package com.demo.todo.domain.task.business;

import com.demo.todo.annotations.Business;
import com.demo.todo.domain.task.converter.TaskConverter;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.model.TaskResponse;
import com.demo.todo.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class TaskBusiness {

    private final TaskService service;
    private final TaskConverter converter;

    public TaskResponse create(TaskRequest request) {
        var entity = service.create(request);
        return converter.toResponse(entity);
    }
}
