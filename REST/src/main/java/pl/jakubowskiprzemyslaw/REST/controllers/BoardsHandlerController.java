package pl.jakubowskiprzemyslaw.REST.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.REST.services.JSONService;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;

import java.util.Optional;

@Controller
public class BoardsHandlerController {

    private final BoardsViewHandler boardsViewHandler;
    private ObjectMapper mapper;
    private final JSONService jsonService;

    @Autowired
    public BoardsHandlerController(BoardsViewHandler boardsViewHandler, ObjectMapper mapper, JSONService jsonService) {
        this.boardsViewHandler = boardsViewHandler;
        this.mapper = mapper;
        this.jsonService = jsonService;
    }

    @PostMapping(value = "/getBoardView")
    public @ResponseBody
    ObjectNode getBoardView() {
        Optional<BoardsView> optionalBoardsView = boardsViewHandler.getView();
        ObjectNode node = mapper.createObjectNode();

        if (optionalBoardsView.isPresent()) {
            node = jsonService.createJSONNodeWithShips(optionalBoardsView.get());
        }
        return node;
    }

    @PostMapping(value = "/getShotBoardView")
    public @ResponseBody
    ObjectNode getShotBoardView() {
        Optional<BoardsView> optionalBoardsView = boardsViewHandler.getView();
        ObjectNode node = mapper.createObjectNode();

        if (optionalBoardsView.isPresent()) {
            node = jsonService.createJSONNodeForPlayerShots(optionalBoardsView.get());
            boardsViewHandler.clear();
        }
        return node;
    }
}
