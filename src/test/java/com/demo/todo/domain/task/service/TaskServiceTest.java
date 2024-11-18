package com.demo.todo.domain.task.service;

import com.demo.todo.db.TaskRepository;
import com.demo.todo.db.entity.TaskEntity;
import com.demo.todo.enums.TaskStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    
    @Mock
    private TaskRepository taskRepository;
    
    @InjectMocks
    private TaskService taskService;
    
    @Test
    @DisplayName("test for create task")
    void create() {
        var title = "test";
        var description = "test description";
        var dueDate = LocalDate.now();
        
        when(taskRepository.save(any(TaskEntity.class)))
            .thenAnswer(invocation -> {
                var e = (TaskEntity) invocation.getArgument(0);
                e.setId(1L);
                e.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                e.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                return e;
            });
        
        var taskEntity = TaskEntity.builder()
            .title(title)
            .description(description)
            .status(TaskStatus.TODO)
            .dueDate(dueDate)
            .build();
        
        var actual = taskService.create(taskEntity);
        
        verify(taskRepository, times(1)).save(any());
        
        assertEquals(1L, actual.getId());
        assertEquals(title, actual.getTitle());
        assertEquals(description, actual.getDescription());
        assertEquals(dueDate, actual.getDueDate());
        assertEquals(TaskStatus.TODO, actual.getStatus());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }
}
