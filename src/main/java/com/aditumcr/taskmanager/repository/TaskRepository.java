package com.aditumcr.taskmanager.repository;

import com.aditumcr.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
