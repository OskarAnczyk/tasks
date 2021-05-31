package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void testSaveAndGetAllTasks(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"testTitle","testContent");
        Task task = taskMapper.mapToTask(taskDto);
        //When
        long id = service.saveTask(taskDto).getId();
        List<TaskDto> taskDtoList = service.getAllTasks();
        //Then
        assertEquals(1,taskDtoList.size());
        //Clean up
        service.deleteTaskById(id);
    }

    @Test
    void testGetTask() throws TaskNotFoundException {
        //Given
        TaskDto taskDto = new TaskDto(2L,"testTitle2","testContent2");
        long id = service.saveTask(taskDto).getId();
        //When
        TaskDto taskDto1 = service.getTask(id);
        //Then
        assertEquals(id,taskDto1.getId());
        //Clean up
        service.deleteTaskById(id);
    }

    @Test
    void testUpdateTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"testTitle","testContent");
        long id = service.saveTask(taskDto).getId();
        //When
        TaskDto taskDto1 = service.updateTask(taskDto);
        //Then
        assertEquals(1L,taskDto1.getId());
        //Clean up
        service.deleteTaskById(id);
    }
}