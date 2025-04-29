package com.aditumcr.taskmanager.controller;

import com.aditumcr.taskmanager.dto.TaskDTO;
import com.aditumcr.taskmanager.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
@Tag(name = "Gestion de Tareas", description = "API para la gestión de tareas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Page<TaskDTO> getAllTasks(
        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
        @RequestParam(required = false) Boolean completed
    ) {
        return taskService.getAllTasks(pageable, completed);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener tarea por ID", description = "Devuelve una tarea específica por su ID")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nueva tarea", description = "Crea una nueva tarea y la devuelve")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tarea", description = "Actualiza una tarea existente y la devuelve")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    @PatchMapping("/{id}/toggle")
    @Operation(summary = "Alternar estado de tarea", description = "Cambia el estado de completada a no completada y viceversa")
    public TaskDTO toggleTaskCompleted(@PathVariable Long id) {
        return taskService.toggleTaskCompleted(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tarea", description = "Elimina una tarea por su ID")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
