package com.demo.todo.domain.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResultResponse {
    
    private boolean isSuccess;
}
