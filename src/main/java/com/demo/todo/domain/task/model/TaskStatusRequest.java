package com.demo.todo.domain.task.model;

import com.demo.todo.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskStatusRequest {

    private TaskStatus status;
}
