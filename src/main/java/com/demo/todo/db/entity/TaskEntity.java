package com.demo.todo.db.entity;

import com.demo.todo.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity(name = "TASK")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class TaskEntity {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String title;
    
    @Column
    private String description;
    
    @Column
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;
    
    @Column(columnDefinition = "DATE")
    private Date dueDate;
    
    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    
    @Column(insertable = false, updatable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
}
