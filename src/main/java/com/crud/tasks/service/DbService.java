package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private TaskMapper taskMapper;

    private final TaskRepository repository;

    public List<TaskDto> getAllTasks() {
        return taskMapper.mapToTaskDtoList(repository.findAll());
    }

    public TaskDto getTask(final Long id) throws TaskNotFoundException{
        return taskMapper.mapToTaskDto(repository.findById(id).orElseThrow(TaskNotFoundException::new));
    }

    public TaskDto updateTask(final TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = repository.save(task);
        return taskMapper.mapToTaskDto(savedTask);
    }

    public void deleteTaskById(final Long id){
        repository.deleteById(id);
    }

    public void saveTask(TaskDto taskDto){
        Task task = taskMapper.mapToTask(taskDto);
        repository.save(task);
    }
}
