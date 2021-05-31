package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    void testTrelloConfigGetters(){
        //Given
        //When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        String trelloToken  = trelloConfig.getTrelloToken();
        String trelloUser = trelloConfig.getTrelloUser();
        //Then
        assertEquals("https://api.trello.com/1",trelloApiEndpoint);
        assertEquals("fff351868acbef717061f0551e15dae2",trelloAppKey);
        assertEquals("45f9cc2a1698321c97c580b74d799de888ab2d06071be3909a2b4dc7c169960d",trelloToken);
        assertEquals("604908dafb18fa672bb4582d",trelloUser);
    }
}