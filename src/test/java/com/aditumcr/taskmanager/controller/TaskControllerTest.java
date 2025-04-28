package com.aditumcr.taskmanager.controller;

import com.aditumcr.taskmanager.dto.TaskDTO;
import com.aditumcr.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void getAllTasks_returnsList() throws Exception {
        TaskDTO task = new TaskDTO();
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk());
        verify(taskService).getAllTasks();
    }

    @Test
    void getTaskById_returnsTask() throws Exception {
        TaskDTO task = new TaskDTO();
        when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk());
        verify(taskService).getTaskById(1L);
    }

    @Test
    void createTask_returnsCreatedTask() throws Exception {
        TaskDTO task = new TaskDTO();
        when(taskService.createTask(any())).thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test\",\"description\":\"Desc\",\"completed\":false}"))
                .andExpect(status().isOk());
        verify(taskService).createTask(any());
    }

    @Test
    void updateTask_returnsUpdatedTask() throws Exception {
        TaskDTO task = new TaskDTO();
        when(taskService.updateTask(eq(1L), any())).thenReturn(task);

        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test\",\"description\":\"Desc\",\"completed\":true}"))
                .andExpect(status().isOk());
        verify(taskService).updateTask(eq(1L), any());
    }

    @Test
    void toggleTaskCompleted_returnsToggledTask() throws Exception {
        TaskDTO task = new TaskDTO();
        when(taskService.toggleTaskCompleted(1L)).thenReturn(task);

        mockMvc.perform(patch("/api/tasks/1/toggle"))
                .andExpect(status().isOk());
        verify(taskService).toggleTaskCompleted(1L);
    }

    @Test
    void deleteTask_deletesTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isOk());
        verify(taskService).deleteTask(1L);
    }
}