package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void testTaskMapperToDto() {
        //Given
        Task task = new Task();
        Task task2 = new Task(1L, "testTitle", "testContent");
        task = task2;
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L, taskDto.getId());
        assertEquals("testTitle", taskDto.getTitle());
        assertEquals("testContent", taskDto.getContent());
    }

    @Test
    void testTaskMapperToDomain(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"testTitle","testContent");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, task.getId());
        assertEquals("testTitle", task.getTitle());
        assertEquals("testContent", task.getContent());
    }
}