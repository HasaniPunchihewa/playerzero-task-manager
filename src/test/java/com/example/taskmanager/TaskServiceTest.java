package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    private final TaskRepository taskRepository =
            Mockito.mock(TaskRepository.class);

    private final TaskService taskService =
            new TaskService(taskRepository);

    @Test
    void shouldReturnTasksByStatus() {

        Task todoTask = new Task(
                "Implement login",
                "Create login functionality",
                TaskStatus.TODO
        );

        when(taskRepository.findByStatus(TaskStatus.TODO))
                .thenReturn(List.of(todoTask));

        List<Task> result =
                taskService.getAllTasks(TaskStatus.TODO);

        assertEquals(1, result.size());
        assertEquals(TaskStatus.TODO, result.get(0).getStatus());
    }

    @Test
    void shouldFilterByStatusInsteadOfReturningAllTasks() {

        Task completedTask = new Task(
                "Write docs",
                "Document the API",
                TaskStatus.COMPLETED
        );

        when(taskRepository.findByStatus(TaskStatus.COMPLETED))
                .thenReturn(List.of(completedTask));

        List<Task> result =
                taskService.getAllTasks(TaskStatus.COMPLETED);

        assertEquals(List.of(completedTask), result);
        verify(taskRepository).findByStatus(TaskStatus.COMPLETED);
        verify(taskRepository, never()).findAll();
    }

    @Test
    void shouldReturnAllTasksWhenStatusIsNull() {

        Task todoTask = new Task(
                "Implement login",
                "Create login functionality",
                TaskStatus.TODO
        );
        Task completedTask = new Task(
                "Write docs",
                "Document the API",
                TaskStatus.COMPLETED
        );

        when(taskRepository.findAll())
                .thenReturn(List.of(todoTask, completedTask));

        List<Task> result = taskService.getAllTasks(null);

        assertEquals(2, result.size());
        verify(taskRepository).findAll();
        verify(taskRepository, never()).findByStatus(any());
    }

    @Test
    void shouldDefaultNewTaskToTodo() {

        Task task = new Task(
                "Create API",
                "Create REST API",
                null
        );

        when(taskRepository.save(task))
                .thenReturn(task);

        Task result = taskService.createTask(task);

        assertEquals(TaskStatus.TODO, result.getStatus());
    }
}