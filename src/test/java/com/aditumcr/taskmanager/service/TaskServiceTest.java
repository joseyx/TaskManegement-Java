package com.aditumcr.taskmanager.service;

import com.aditumcr.taskmanager.dto.TaskDTO;
import com.aditumcr.taskmanager.model.Task;
import com.aditumcr.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks_returnsList() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Test");
        task.setDescription("Desc");
        task.setCompleted(false);

        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));

        List<TaskDTO> result = taskService.getAllTasks();
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void getTaskById_returnsTask() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Test");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskDTO dto = taskService.getTaskById(1L);
        assertEquals("Test", dto.getName());
    }

    @Test
    void createTask_savesAndReturnsTask() {
        TaskDTO dto = new TaskDTO();
        dto.setName("New Task");
        dto.setDescription("Desc");
        dto.setCompleted(false);

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setName("New Task");
        savedTask.setDescription("Desc");
        savedTask.setCompleted(false);

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        TaskDTO result = taskService.createTask(dto);
        assertEquals("New Task", result.getName());
        assertEquals("Desc", result.getDescription());
    }

    @Test
    void updateTask_updatesAndReturnsTask() {
        Task existing = new Task();
        existing.setId(1L);
        existing.setName("Old");
        existing.setDescription("Old Desc");
        existing.setCompleted(false);

        TaskDTO dto = new TaskDTO();
        dto.setName("Updated");
        dto.setDescription("Updated Desc");
        dto.setCompleted(true);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        TaskDTO result = taskService.updateTask(1L, dto);
        assertEquals("Updated", result.getName());
        assertEquals("Updated Desc", result.getDescription());
        assertTrue(result.isCompleted());
    }

    @Test
    void deleteTask_callsRepository() {
        taskService.deleteTask(1L);
        verify(taskRepository).deleteById(1L);
    }

    @Test
    void toggleTaskCompleted_togglesAndReturnsTask() {
        Task task = new Task();
        task.setId(1L);
        task.setCompleted(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        TaskDTO result = taskService.toggleTaskCompleted(1L);
        assertTrue(result.isCompleted());
    }
}
