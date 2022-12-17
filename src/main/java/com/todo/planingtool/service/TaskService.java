package com.todo.planingtool.service;

import com.todo.planingtool.persistance.model.Task;
import com.todo.planingtool.persistance.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(String taskInfo) {
        return taskRepository.insert(constructTask(taskInfo));
    }

    //pattern Builder
    private Task constructTask (String taskInfo) {
        return Task.builder()
                .taskInfo(taskInfo)
                .creationDate(LocalDateTime.now())
                .completionStatus(false)
                .build();
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
