package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    @DisplayName("Map to Dto")
    void testTrelloMapperToDto(){
        //Given
        TrelloList trelloList = new TrelloList("1","testList",false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        TrelloBoard trelloBoard = new TrelloBoard("1","testBoard",trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        TrelloCard trelloCard = new TrelloCard("testCard","testDescription","1","1");
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloList.getName(),trelloListDtos.get(0).getName());
        assertEquals(trelloBoard.getName(),trelloBoardDtos.get(0).getName());
        assertEquals(trelloCard.getName(),trelloCardDto.getName());
    }

    @Test
    @DisplayName("Map to domain")
    void testTrelloMapperToDomain(){
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","testListDto",false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","testBoardDto",trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);
        TrelloCardDto trelloCardDto = new TrelloCardDto("testCardDto","testDescription","1","1");
        //WHen
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloListDto.getName(),trelloLists.get(0).getName());
        assertEquals(trelloBoardDto.getName(),trelloBoards.get(0).getName());
        assertEquals(trelloCardDto.getName(),trelloCard.getName());
    }
}
