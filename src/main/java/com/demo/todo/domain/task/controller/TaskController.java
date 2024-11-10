package com.demo.todo.domain.task.controller;

import com.demo.todo.domain.task.business.TaskBusiness;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.model.TaskResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskBusiness business;
    
    /**
     * create new task
     * @param request data of new task
     * @return create task
     */
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        var response = business.create(request);
        return ResponseEntity.ok(response);
    }
}
