package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @Test
    void testGetEmptyList() throws Exception{
        //Given
        when(service.getAllTasks()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void testGetTasks() throws Exception{
        //Given
        List<TaskDto> taskDtoList = List.of(new TaskDto(1L,"testTitle","testContent"));
        when(service.getAllTasks()).thenReturn(taskDtoList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                    .get("/v1/task/getTasks")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title",Matchers.is("testTitle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content",Matchers.is("testContent")));
    }

    @Test
    void testGetTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L,"testTitle","testContent");
        when(service.getTask(1L)).thenReturn(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("taskId","1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",Matchers.is("testTitle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("testContent")));
    }

    @Test
    void testCreateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L,"testTitle","testContent");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                    .post("/v1/task/createTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testUpdateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L,"testTitle","testContent");
        TaskDto savedTaskDto = new TaskDto(2L,"testTitle2","testContent2");

        when(service.updateTask(any())).thenReturn(savedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                    .put("/v1/task/updateTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",Matchers.is("testTitle2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("testContent2")));
    }

    @Test
    void testDeleteTask() throws Exception{
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                    .delete("/v1/task/deleteTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("taskId","1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}