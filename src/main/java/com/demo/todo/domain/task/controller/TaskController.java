package com.demo.todo.domain.task.controller;

import com.demo.todo.domain.task.business.TaskBusiness;
import com.demo.todo.domain.task.model.ResultResponse;
import com.demo.todo.domain.task.model.TaskRequest;
import com.demo.todo.domain.task.model.TaskResponse;
import com.demo.todo.domain.task.model.TaskStatusRequest;
import com.demo.todo.enums.TaskStatus;
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
    
    /**
     * get tasks by status
     * @param status status
     * @return list of searched tasks
     */
    @GetMapping(path = "/status/{status}")
    public ResponseEntity<List<TaskResponse>> getTaskByStatus(@PathVariable TaskStatus status) {
        var taskList = business.getByStatus(status);
        return ResponseEntity.ok(taskList);
    }
    
    /**
     * @return task status list
     */
    @GetMapping(path = "/status")
    public ResponseEntity<List<TaskStatus>> getAllStatus() {
        var statusList = business.getAllStatus();
        return ResponseEntity.ok(statusList);
    }
    
    /**
     * update task by id
     * @param id task id to update
     * @param task task data
     * @return updated task data
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<TaskResponse> updateTask(
        @PathVariable Long id,
        @RequestBody TaskRequest task
    ) {
        var response = business.updateTask(id, task);
        return ResponseEntity.ok(response);
    }
    
    /**
     * update task status
     * @param id task id to update
     * @param statusRequest status
     * @return update task data
     */
    @PatchMapping(path = "/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(
        @PathVariable Long id,
        @RequestBody TaskStatusRequest statusRequest
        ) {
        var response = business.updateTaskStatus(id, statusRequest);
        return ResponseEntity.ok(response);
    }
    
    /**
     * delete task
     * @param id task id to delete
     * @return delete result
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResultResponse> deleteTask(@PathVariable Long id) {
        var response = business.deleteTask(id);
        return ResponseEntity.ok(new ResultResponse(response));
    }
}
