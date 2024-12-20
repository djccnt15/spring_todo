package com.demo.todo.db;

import com.demo.todo.db.entity.TaskEntity;
import com.demo.todo.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    
    List<TaskEntity> findByDueDate(LocalDate dueDate);
    
    List<TaskEntity> findByStatus(TaskStatus status);
}
