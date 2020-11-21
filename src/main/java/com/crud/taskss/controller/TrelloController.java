package com.crud.taskss.controller;

import com.crud.taskss.domain.CreatedTrelloCard;
import com.crud.taskss.domain.TrelloBoardDto;
import com.crud.taskss.domain.TrelloCardDto;
import com.crud.taskss.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//       trelloBoards.stream()
//               .filter(s -> s.optional(s.getId()).isPresent())
//               .filter(s -> s.optional(s.getName()).isPresent())
//                .filter(s -> s.getName().contains("Kodilla"))
//                .forEach(s -> {
//                    System.out.println(s.getId() + " - " + s.getName());
//                    System.out.println("This board contains lists: ");
//
//                    s.getLists().forEach(trelloList ->
//                            System.out.println(trelloList.getName() + " - " + trelloList.getId() + " " + trelloList.isClosed()));
//                });

    }
    @RequestMapping(method = RequestMethod.POST, value ="createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return trelloClient.createNewCard(trelloCardDto);
    }

}
