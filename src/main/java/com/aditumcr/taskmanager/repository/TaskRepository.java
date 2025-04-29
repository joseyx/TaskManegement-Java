package com.aditumcr.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aditumcr.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByCompleted(Boolean completed, Pageable pageable);
}