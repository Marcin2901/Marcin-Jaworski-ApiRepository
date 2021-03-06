package com.crud.taskss.trello.client;


import com.crud.taskss.domain.CreatedTrelloCardDto;
import com.crud.taskss.domain.TrelloBoardDto;
import com.crud.taskss.domain.TrelloCardDto;
import com.crud.taskss.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @BeforeEach
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
    }

    @Test
     void shouldFetchTrelloBoards() throws URISyntaxException {

        //Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_board", "test_id", new ArrayList<>());

        URI uri = new URI("http://test.com/members/marcinjaworski25/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
       // When
         List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
        //   Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());

    }


//    @Test
//    public void shouldCreateCard() throws URISyntaxException {
//        //Given
//        TrelloCardDto trelloCardDto = new TrelloCardDto(
//                "Task task",
//                "Test Descruiption",
//                "top",
//                "test_id"
//        );
//        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");
//
//        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto(
//                "1",
//                "Test task",
//                "http://test.com"
//
//        );
//        when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCard);
//        //When
//        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
//        //Then
//        assertEquals("1", newCard.getId());
//        assertEquals("Test task", newCard.getName());
//        assertEquals("http://test.com", newCard.getShortUrl());

   // }

    // 18.4
    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/marcinjaworski25/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);

        List<TrelloBoardDto> boards = trelloClient.getTrelloBoards();


        assertEquals(0, boards.size());

    }
}