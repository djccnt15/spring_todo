package com.demo.todo.domain.task.controller;

import com.demo.todo.domain.task.business.TaskBusiness;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.model.TaskResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    
    /**
     * get task by id
     * @param id task id
     * @return task
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(business.getById(id));
    }
    
    /**
     * get tasks by due date
     * @param dueDate due date
     * @return list of searched tasks
     */
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTaskByDueDate(Optional<LocalDate> dueDate) {
        if (dueDate.isPresent()) {
            var result = business.getByDueDate(dueDate.get());
            return ResponseEntity.ok(result);
        }
        var response = business.getAll();
        return ResponseEntity.ok(response);
    }
}
